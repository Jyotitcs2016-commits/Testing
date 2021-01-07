package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.formula.functions.Value;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFDxfStyleProvider;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;

public class CustomMethods {
	
	
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static TreeMap<String, ArrayList<String>> map;
	
	
	public static void highLightElement(WebDriver driver, WebElement element)
	{
	JavascriptExecutor js=(JavascriptExecutor)driver; 

	js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);

	try 
	{
	Thread.sleep(1000);
	} 
	catch (InterruptedException e) {

	System.out.println(e.getMessage());
	} 

	js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element); 

	}

		
	
public static WebElement return_element(WebDriver driver,String Xpath,String attr) {
	WebElement element=null;
	List<WebElement> elements=driver.findElements(By.xpath(Xpath));
	for(int i=0;i<=elements.size();i++) {
		
		if(elements.get(i).getAttribute("innerText")==attr) {
			
			element=elements.get(i);
			break;
			
			
		}
		
	}
	
	return element;
		
}	
	
public void take_screenshot(WebDriver driver) throws IOException {
		
		LocalDateTime today=LocalDateTime.now();
		DateTimeFormatter formatToday=DateTimeFormatter.ofPattern("MM_dd_yy_hh_mm_ss");
		
		String filename=today.format(formatToday);
		
		driver = (RemoteWebDriver) new Augmenter().augment(driver);
		File srcFile = (File) ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"/screenshot/"+filename+".png"));
		
	}
	
	public int last_row(String pathname,int sheetno) throws Exception {
		File file=new File(pathname);
		FileInputStream fis=new FileInputStream(file);
		wb=new XSSFWorkbook(fis);
		sheet=wb.getSheetAt(sheetno);
		int row=sheet.getLastRowNum();
		row=row+1;		
		return row;	
		
	}
	public String get_row_data(int row,int col,String pathname) throws Exception {
		
		File file=new File(pathname);
		FileInputStream fis=new FileInputStream(file);
		wb=new XSSFWorkbook(fis);
		
		String rowdata;
		
		sheet=wb.getSheetAt(0);
		//row=row+1;
		rowdata=sheet.getRow(row).getCell(col).getStringCellValue();		
			
		return rowdata;		
	}
	
	
	@SuppressWarnings("deprecation")
	public void read_xssfworkbook(String pathname) throws Exception {
		File file=new File(pathname);
		FileInputStream fis=new FileInputStream(file);
		wb=new XSSFWorkbook(fis);
		XSSFRow row;
		 if(file.isFile() && file.exists()) {
	         System.out.println("openworkbook.xlsx file open successfully.");
	     
	         XSSFSheet sheet=wb.getSheetAt(0);
	         Iterator<Row> rowiterate=sheet.iterator();
	         
		 while(rowiterate.hasNext()) {
			 
			 row=(XSSFRow) rowiterate.next();
			 Iterator<Cell> celliterator=row.cellIterator();
			 
			 while(celliterator.hasNext()) {
				 Cell cell = celliterator.next();
				switch(cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
	                  System.out.print(cell.getNumericCellValue() + " \t\t ");
	                  break;
	               
	               case Cell.CELL_TYPE_STRING:
	                  System.out.print(
	                  cell.getStringCellValue() + " \t\t ");
	                  break;				
				}				 
			 }
			 
			 System.out.println();			 
		 }
		 fis.close();
		 }
			 
		 
		 
		  else {
	         System.out.println("Error to open openworkbook.xlsx file.");
	      }
		 
		 
		 
		
	}
	public void write_xssfworkbook(String out_path) throws Exception {
		wb=new XSSFWorkbook();
		XSSFSheet spreadsheet = wb.createSheet("Employee_data");
		XSSFRow row = spreadsheet.createRow((short)1);
		
		 Map < String, Object[] > empinfo = 
			      new TreeMap < String, Object[] >();
			      empinfo.put( "1", new Object[] { "EMP ID", "EMP NAME", "DESIGNATION" });
			      empinfo.put( "2", new Object[] { "tp01", "Gopal", "Technical Manager" });
			      empinfo.put( "3", new Object[] { "tp02", "Manisha", "Proof Reader" });
			      empinfo.put( "4", new Object[] { "tp03", "Masthan", "Technical Writer" });
			      empinfo.put( "5", new Object[] { "tp04", "Satish", "Technical Writer" });
			      empinfo.put( "6", new Object[] { "tp05", "Krishna", "Technical Writer" });
			
		Set<String> keyid = empinfo.keySet();
		int rowid=0;
			for(String key:keyid) {
				row=spreadsheet.createRow(rowid++);
				Object [] objectarr=empinfo.get(key);
				int cellid=0;
				for (Object obj:objectarr) {
					Cell cell = row.createCell(cellid++);
					cell.setCellValue((String) obj);					
				}
			}
		
		try {
			FileOutputStream fout = new FileOutputStream(new File(out_path));
			wb.write(fout);
			fout.close();
			System.out.println("file created at "+out_path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			      
		
	}
	
	public TreeMap<String,ArrayList<String>> get_row_map(String path) {
		
		try {
			//map.clear();
			File file=new File(path);
			FileInputStream fis=new FileInputStream(file);
			wb=new XSSFWorkbook(fis);
			sheet=wb.getSheetAt(0);
			//String empname = null;
			
			Row r = sheet.getRow(0);
			int maxCell=  r.getLastCellNum();
			int maxrow=sheet.getLastRowNum();
			System.out.println(maxCell);
			System.out.println(maxrow);
			map=new TreeMap<String, ArrayList<String>>();
			ArrayList<String> data = new ArrayList<String>();
			for (int i = 0; i < maxrow; i++) {
				for (int j = 0; j < maxCell; j++) {
				
				String key=sheet.getRow(0).getCell(j).getStringCellValue();
				//data=sheet.getRow(i+1).getCell(j).getStringCellValue();
				
			//	data.add(value);
				
				
			map.put(key, data);
			}
				
			}
			System.out.println(map.get("Last Name"));
			
						
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return map;
		
			
	}
	
	
		
	public static void main(String[] args) throws Exception {
			String path="C:\\Users\\jyoti\\Downloads\\Sample.java\\autonomiq-template\\resources\\";
			CustomMethods nw=new CustomMethods();
			Object[][] data=nw.read_data(0,path+"TD_Create User.xlsx");
			System.out.println(data);
		}

	
	public  Object[][] read_data(int sheetno,String path) throws Exception {
		
		File file=new File(path);
		FileInputStream fis=new FileInputStream(file);
		wb=(XSSFWorkbook) WorkbookFactory.create(fis);
		sheet=wb.getSheetAt(sheetno);
		
		Row r = sheet.getRow(0);
		int maxCell=  r.getLastCellNum();
		int maxrow=sheet.getLastRowNum();
		//logic to get row cell in object values for a column
		
		Object[][] data=new Object[maxrow][maxCell];
		
		for (int i = 0; i < maxrow; i++) {
			for (int j = 0; j < maxCell; j++) {
				
				data[i][j]=sheet.getRow(i+1).getCell(j).getStringCellValue();
				
				//
			}
			
		}
		System.out.println(data[0][1]);
		
		return data;
		
		
		
	}
	
	
	
	
	
	
	
	
	public static ArrayList<Object[]> read_xls_data(String path,int sheetno) throws Exception{
		File file=new File(path);
		FileInputStream fis=new FileInputStream(file);
		wb=new XSSFWorkbook(fis);
		sheet=wb.getSheetAt(sheetno);
		
	
		ArrayList<Object[]> data_reader=new ArrayList<Object[]>();
		
		int rowcount=sheet.getLastRowNum();
		
		for (int i = 0; i < rowcount; i++) {
			
			
			
			
			
			
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return data_reader;
		
		
		
		
		
		
		
		
		
	}	
		
		
		
		
		
	
	
	
}
