package VideoDownloader;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Chrome {

	
	public WebDriver CreateChromeDriver() {
		System.setProperty("webdriver.chrome.driver","G://Selenium jar and drivers//chromedriver/chromedriver.exe");
		WebDriver chromeDriver = new ChromeDriver();
		return chromeDriver;
	}
	
	public void NavigateToURL(String url, WebDriver driver) {
		driver.get(url);
	}
	
	public void MaximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	public List<WebElement> FindVideoLinksByXPATH(String url,WebDriver driver) {
		NavigateToURL(url,driver);
		List<WebElement> links = driver.findElements(By.xpath("//*[@id=\"video-title\"]"));
		return links;
	}
	
	
}
