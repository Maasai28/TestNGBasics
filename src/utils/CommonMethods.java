package utils;
//control + O lists all methods in package
import java.util.List;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;

public class CommonMethods {

	public static WebDriver driver;

	public static void setUpDriver(String browser, String url) {
		if (browser.equalsIgnoreCase("chrome")) {
			// For mac
			System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
			// for windows
			// System.setProperty("webdriver.chrome.driver",
			// "src/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "src/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		} else {
			System.out.println("browser given is wrong");
		}
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		driver.get(url);
	}

	/**
	 * This method will select a specified value from a drop down
	 * @author Syntax 
	 * @param Select element, String text
	 */
	public static void selectValueFromDD(WebElement element, String text) {
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		boolean isSelected=false;
		for (WebElement option : options) {
			String optionText = option.getText();
			if (optionText.equals(text)) {
				select.selectByVisibleText(text);
				System.out.println("Option with text "+text+" is selected");
				isSelected=true;
				break;
			}
		}
		if(!isSelected) {
			System.out.println("Option with text +"+text+"is not available");
		}
	}

	/**
	 * This method will select a specified value from a drop down by its index
	 * @author Syntax 
	 * @param Select element, int index
	 */
	public static void selectValueFromDD(WebElement element, int index) {
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		if (options.size() > index) {
			select.selectByIndex(index);
		} else {
			System.out.println("Invalid index has been passed");
		}
	}

	public static void sendText(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}
/**
 * Method will accept alert
 * @throws NoAlertPresentException if alert is not present
 */
	public static void acceptAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (NoAlertPresentException e) {
			System.out.println("Alert was not present");
		}
	}
	/**
	 * Method will dismiss alert
	 * @throws NoAlertPresentException if alert is not present
	 */
	public static void dismissAlert() {
		try {
			Alert alert=driver.switchTo().alert();
			alert.dismiss();
		}catch (NoAlertPresentException e) {
			System.out.println("Alert was not present");
		}
	}
	/**
	 * Method will get text of an alert
	 * @throws NoAlertPresentException if alert is not present
	 * @return String text
	 */
	public static String getAlertText() {
		
		try {
			Alert alert=driver.switchTo().alert();
			return alert.getText();
		}catch (NoAlertPresentException e) {
			System.out.println("Alert was not present");
			return null;
		}
		
	}
	/**
	 * This method send String keys into textbox
	 * @author KyungrinSeo
	 * @param WebElement element, String keys
	 * @exception FrameNotFoundException
	 * */
	public static void sendKeys(WebElement element, String keys) {
		element.clear();
		element.sendKeys(keys);
	}
	/**
	 * Method that will switch control to the specified frame
	 * @param frame id or frame name
	 */
	public static void switchToFrame(String idOrName) {
		try {
			driver.switchTo().frame(idOrName);
		}catch(NoSuchFrameException e) {
			System.out.println("Frame is not present");
		}
	}
	/**
	 * Method that will switch control to the specified frame
	 * @param frame element
	 */
	public static void switchToFrame(WebElement element) {
		try {
			driver.switchTo().frame(element);
		}catch(NoSuchFrameException e) {
			System.out.println("Frame is not present");
		}
	}
	/**
	 * Method that will switch control to the specified frame
	 * @param frame index
	 */
	public static void switchToFrame(int index) {
		try {
			driver.switchTo().frame(index);
		}catch(NoSuchFrameException e) {
			System.out.println("Frame is not present");
		}
	}
	/**
	 * Method that will check specified radio button
	 * @param List<WebElement> list, String text
	 */
	public static void checkRadioButton(List<WebElement> list, String text) {
		boolean isSelected = false;
		for(WebElement e:list) {
			if(e.isEnabled()) {
				//System.out.println(e.getText());
				if(e.getText().contains(text)) {
					System.out.println(text + " is selected");
					isSelected = true;
					e.click();
					break;
				}
			} else {
				System.out.println("Button is NOT enabled");
			}
		}
		if(!isSelected) {
			System.out.println(text + " is NOT selected");
		}
	}
	/**
	 * Need to facinate 
	 * 
	 * 
	 * Method that will check specified checkbox button
	 * @param List<WebElement> list, String text
	 */
	public static void checkCheckBoxes(List<WebElement> list, String text) {
		boolean isSelected = false;
		for(WebElement e:list) {
			if(e.isEnabled()) {
				if(e.getText().contains(text)) {
					System.out.println(text + " is selected");
					isSelected = true;
					e.click();
					break;
				}
			}else {
				System.out.println("CheckBox is NOT enabled");
			}
		}
		if(!isSelected) {
			System.out.println(text + " is NOT selected");
		}
	}
}