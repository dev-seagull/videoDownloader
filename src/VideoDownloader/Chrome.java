package VideoDownloader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Chrome {

	private WebDriver CreateChromeDriver() {
		System.setProperty("webdriver.chrome.driver","G://Selenium jar and drivers//chromedriver/chromedriver.exe");
		WebDriver chromeDriver = new ChromeDriver();
		return chromeDriver;
	}
	
	private void NavigateToURL(String url) {
		WebDriver chromeDriver = CreateChromeDriver();
		chromeDriver.get(url);
	}
	
	private void MaximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}
}
