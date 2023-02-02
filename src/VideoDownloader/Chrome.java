package VideoDownloader;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Chrome {

	
	public WebDriver CreateChromeDriverObject() {
		System.setProperty("webdriver.chrome.driver","G://Selenium jar and drivers//chromedriver/chromedriver.exe");
		return new ChromeDriver();
	}
	
	
}
