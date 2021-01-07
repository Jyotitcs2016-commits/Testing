package utilities;

import org.openqa.selenium.interactions.Actions;

import io.qameta.allure.Attachment;

public class AllureHelp {
	@Attachment
	public static String performedActions(Actions actionSequence) {
	    return actionSequence.toString();
	}

	@Attachment(value = "Page screenshot", type = "image/png")
	public static byte[] saveScreenshot(byte[] screenShot) {
	    return screenShot;
	}
}
