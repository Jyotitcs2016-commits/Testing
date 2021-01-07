package utilities;

import static org.testng.Assert.assertFalse;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



public class AutonomiqMethods {
	
	 Map<String, Statement> db_conn_hash = new HashMap<String, Statement>();
	    private RemoteWebDriver driver = null;
	    DesiredCapabilities dc = new DesiredCapabilities();
	    private StringBuffer verificationErrors = new StringBuffer();
	    private Statement stmt = null;
	    private Connection conn = null;
	    public ResultSet rs = null;
	    public boolean cond;
	    ExtentHtmlReporter htmlReporter;
	    ExtentReports extent;
	    ExtentTest logger;
	    private String previousStep="";
	    private Boolean isSaucelabs = false;
	    public String shell_return="";
	    public Map<String, String> tmp_hash = null;
	    private SoftAssert softAssert = new SoftAssert();
	    JavascriptExecutor executor;
	    
	    public void writeByteFile (String fileName, String content) throws Exception {
		    FileOutputStream outputStream = new FileOutputStream(fileName);
		    byte[] strToBytes = content.getBytes();
		    outputStream.write(strToBytes);
		    outputStream.close();
		}    
	    
	   
	public void waitTillElementGetsEnabled(String Xpath, WebDriver driver, JavascriptExecutor executor) {
        try {
            if (Xpath == null) {
                new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(driver.switchTo().activeElement()));
            }
            else {
                new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Xpath)));
            }
        } catch (Exception e) {
            try {
                if (Xpath == null) {
                    executor.executeScript("arguments[0].scrollIntoView();", driver.switchTo().activeElement());
                    new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(driver.switchTo().activeElement()));
                }
                else {
                    executor.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(Xpath)));
                    new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(Xpath)));
                }
            } catch (Exception e1) {
                System.out.println("Element is not enabled");
            }
        }
    }


    public void waitTillElementGetsDisabled(String Xpath, WebDriver driver) {
        try {
            new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(Xpath)));
        } catch (StaleElementReferenceException e) {
            System.out.println("Stale element");
        } catch (Exception e) {
            if (driver.findElement(By.xpath(Xpath)).isDisplayed()) {
                assertFalse(driver.findElement(By.xpath(Xpath)).isDisplayed(), "Element is visible");
            } else {
                throw new java.lang.Error(e);
            }
        }
    }


    public void waitTillElementGetsInvisible(String xpath, int main_count, JavascriptExecutor js ) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            int count = 0;
            int last_count = 0;
            Boolean permission_to_poll = true;
            String scriptSetAttrValue = "arguments[0].setAttribute(arguments[1],arguments[2])";

            long lStartTime = 0;

            while (permission_to_poll) {
                System.out.println(System.currentTimeMillis());
                List<WebElement> elem_list = driver.findElements(By.xpath(xpath));
                System.out.println(System.currentTimeMillis());
                count = 0;
                try {

                    if(elem_list.size() > 0) {
                        for (WebElement obj: elem_list){

                            count +=1;
                            System.out.println("count:" + count);
                            try {
                                if (!(obj.isDisplayed())) {
                                    continue;

                                }

                                int width = Integer.parseInt(obj.getAttribute("offsetWidth"));
                                int height = Integer.parseInt(obj.getAttribute("offsetHeight"));

                                if (width <= 0 | height <= 0){
                                    continue;
                                }
                                 System.out.println("actually waiting" );
                                new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
                                System.out.println("waiting  is over" );
                            }catch (StaleElementReferenceException e){
                                e.printStackTrace();
                                System.out.println("Stale error:" + e);
                            }
                        }
                    }
                } catch (Exception err) {
                    err.printStackTrace();
                    System.out.println("Err:" + err);
                }
                if (last_count == count & lStartTime == 0 ){
                    lStartTime = System.currentTimeMillis();
                    System.out.println("lStartTime:" + lStartTime);
                }
                System.out.println("count:" + count);
                System.out.println("last_count:" + last_count);
                if (count > last_count){
                    lStartTime = 0;

                }
                System.out.println("main_count:" + main_count);
                if (lStartTime > 0){
                    if (main_count >= count && (System.currentTimeMillis() - lStartTime) > 3000){
                        System.out.println("permission_to_poll:" + permission_to_poll);
                        System.out.println(System.currentTimeMillis());


                        permission_to_poll = false;

                    }
                    else if (main_count < count && (System.currentTimeMillis() - lStartTime) > 3000){
                        System.out.println("permission_to_poll:" + permission_to_poll);
                        System.out.println(System.currentTimeMillis());
                        permission_to_poll = false;
                        System.out.println("permission_to_poll:" + permission_to_poll);
                    }
                }

                last_count = count;
                Thread.sleep(20);
            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        } catch (Exception e) {
            //ignore
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }


    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }

    }

    private void waitUntilAlertBoxPresent() {
        long t = System.currentTimeMillis();
        long end = t + 30000;
        boolean flag = false;
        while (System.currentTimeMillis() < end || !flag) {
            if (isAlertPresent()) {
                flag = true;
            }
        }
        if(!flag) {
            System.out.println("ALERT Box Not Found");
            throw new java.lang.Error("ALERT Box Not Found");
        }

    }

    private void switchToDefaultFrame() {
        if (!isAlertPresent()) {
            driver.switchTo().defaultContent();
        } else {
            logger.info("Popup is present");
        }
    }

    private String get_alert_text(){
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    private void notification(String message) throws Exception {
        try {

            if (!isAlertPresent()) {

                String javaScriptStr = "var style = document.createElement('style');\n" +
                        "style.type = 'text/css';\n" +
                        "style.innerHTML = `.aut-toastify{padding:12px 20px;color:#fff;display:inline-block;box-shadow:0 3px 6px -1px rgba(0,0,0,.12),0 10px 36px -4px rgba(77,96,232,.3);background:-				webkit-linear-gradient(315deg,#73a5ff,#5477f5);background:linear-gradient(135deg,#73a5ff,#5477f5);position:fixed;z-index:5000;opacity:0;transition:all .4s cubic-bezier(.215,.61,.355,1);border-radius:2px;cursor:pointer;text-decoration:none;max-width:calc(50% - 20px)}.aut-toastify.on{opacity:1}.aut-right{right:15px}.aut-left{left:15px}@media only screen and (max-width:360px){.aut-left,.aut-right{margin-left:auto;margin-right:auto;left:0;right:0;max-width:fit-content}}`;\n" +
                        "document.getElementsByTagName('head')[0].appendChild(style);\n" +
                        "!function(t,o){\"object\"==typeof module&&module.exports?(require(\"./toastify.css\"),module.exports=o()):t.Toastify=o()}(this,function(t){var i=function(t){return new i.lib.init(t)};function r(t,o){return!(!t||\"string\"!=typeof o||!(t.className&&-1<t.className.trim().split(/\\s+/gi).indexOf(o)))}return i.lib=i.prototype={toastify:\"1.2.2\",constructor:i,init:function(t){return t||(t={}),this.options={},this.options.text=t.text||\"Hi there!\",this.options.duration=t.duration||3e3,this.options.selector=t.selector,this.options.callback=t.callback||function(){},this.options.destination=t.destination,this.options.newWindow=t.newWindow||!1,this.options.gravity=\"bottom\"==t.gravity?\"bottom\":\"top\",this.options.positionLeft=t.positionLeft||!1,this.options.backgroundColor=t.backgroundColor,this.options.avatar=t.avatar||\"\",this.options.className=t.className||\"\",this},buildToast:function(){if(!this.options)throw\"Toastify is not initialized\";var t=document.createElement(\"div\");if(t.className=\"aut-toastify on \"+this.options.className,!0===this.options.positionLeft?t.className+=\" aut-left\":t.className+=\" aut-right\",t.className+=\" \"+this.options.gravity,this.options.backgroundColor&&(t.style.background=this.options.backgroundColor),t.innerHTML=this.options.text,\"\"!==this.options.avatar){var o=document.createElement(\"img\");o.src=this.options.avatar,o.className=\"avatar\",!0===this.options.positionLeft?t.appendChild(o):t.insertAdjacentElement(\"beforeend\",o)}elements=document.getElementsByClassName(\"aut-toastify on\");var i=elements;if(0<elements.length)for(var n=i.length-1;0<=n;n--){i[n].parentNode.removeChild(i[n])}return void 0!==this.options.destination&&t.addEventListener(\"click\",function(t){t.stopPropagation(),!0===this.options.newWindow?window.open(this.options.destination,\"_blank\"):window.location=this.options.destination}.bind(this)),t},showToast:function(){var t,o=this.buildToast();if(!(t=void 0===this.options.selector?document.body:document.getElementById(this.options.selector)))throw\"Root element is not defined\";return t.appendChild(o),i.reposition(),o.timeOutValue=window.setTimeout(function(){this.removeElement(o)}.bind(this),this.options.duration),this},removeElement:function(t){}},i.reposition=function(){for(var t,o={top:15,bottom:15},i={top:15,bottom:15},n={top:15,bottom:15},e=document.getElementsByClassName(\"aut-toastify\"),s=0;s<e.length;s++){t=!0===r(e[s],\"top\")?\"top\":\"bottom\";var a=e[s].offsetHeight;(0<window.innerWidth?window.innerWidth:screen.width)<=360?(e[s].style[t]=n[t]+\"px\",n[t]+=a+15):!0===r(e[s],\"left\")?(e[s].style[t]=o[t]+\"px\",o[t]+=a+15):(e[s].style[t]=i[t]+\"px\",i[t]+=a+15)}return this},i.lib.init.prototype=i.lib,i});\n";
                ((JavascriptExecutor) driver).executeScript(String.format("%s", String.valueOf(javaScriptStr).replaceAll("\n", "")));
                ((JavascriptExecutor) driver).executeScript(String.format("Toastify({\n" +
                        "    text: \"%s\",\n" +
                        "    duration: 150000,\n" +
                        "    gravity: \"top\", // `top` or `bottom`\n" +
                        "    positionLeft: false, // `true` or `false`\n" +
                        "    backgroundColor: \"linear-gradient(to right, #00b09b, #96c93d)\",\n" +
                        "    }).showToast();".replaceAll("\n", ""), message));
                Thread.sleep(700);
            }

        } catch (Exception e1) {
            //ignore
        }
    }
    public void CustomClick(String Xpath, WebDriver driver, JavascriptExecutor executor, String option, Actions action) {

    	WebElement element=driver.findElement(By.xpath(Xpath));
        
        try {
        	
        	
        	 
           switch (option) {
                case "click":
                    element.click();
                    break;
                case "double click":
                    action.doubleClick(element).perform();
                    break;
                case "right click":
                    action.contextClick(element).perform();
                    break;
                case "action chain":
                    action.moveToElement(element).click().perform();
                    break;
                case "click active":
                    driver.switchTo().activeElement().click();
                default:
                    break;
            }
        } catch (Exception e) {
	        	
	        	
           switch (option) {
                case "click":
                    executor.executeScript("arguments[0].click();", element);
                    break;
                case "double click":
                    executor.executeScript("arguments[0].dblclick();", element);
                    break;
                case "right click":
                    executor.executeScript("arguments[0].contextmenu();", element);
                    break;
                case "click active":
                    executor.executeScript("arguments[0].click();",element);
                default:
                    break;
            }
        }
    }

    private void hitAction(String xpath, String key){
        try {
            driver.findElement(By.xpath(xpath))
                    .sendKeys(key);
        }catch (Exception e){
            e.printStackTrace();
            Actions action = new Actions(driver);
            action.sendKeys(key);
        }
    }

    private void validateAttrValue(String xpath, String data, String attr, Boolean isSoftAssert) {
        if (isSoftAssert) {
            softAssert.assertEquals(data, driver.findElement(By.xpath(xpath)).getAttribute(attr));
        } else {
            Assert.assertEquals(data, driver.findElement(By.xpath(xpath)).getAttribute(attr));
        }
    }

    private String getCssPropertyValue(WebElement elem, String property) {
        Object value = ((JavascriptExecutor) driver).executeScript("return window.getComputedStyle(arguments[0], null).getPropertyValue('" + property + "')", elem);
        return value.toString();
    }

    private void validateCssProperty(String xpath, String data, String property, Boolean isSoftAssert) {
        WebElement elem = driver.findElement(By.xpath(xpath));
        String value = getCssPropertyValue(elem, property);
        if (isSoftAssert) {
            softAssert.assertEquals(data, value);
        } else {
            Assert.assertEquals(data, value);
        }
    }

    public void takeSnapShot(RemoteWebDriver webdriver, String fileWithPath) throws Exception {
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);
    }

    public void switch_tab(int tab) {
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tab));
    }

    public String GetText(String xpath) {

        if (driver.findElement(By.xpath(xpath)).getText() != null) {
            return driver.findElement(By.xpath(xpath)).getText().trim();
        } else if (driver.findElement(By.xpath(xpath)).getAttribute("textContent") != null) {
            return driver.findElement(By.xpath(xpath)).getAttribute("textContent").trim();
        }

        return "";
    }
}
