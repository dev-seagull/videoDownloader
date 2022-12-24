package VideoDownloader;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Youtube{
	
	Chrome chrome = new Chrome();
	
	public void SearchAndGetVideos(String searchURL,WebDriver driver) throws InterruptedException{
		driver.get(searchURL);
		
		
		int counter=0;
		while(counter<100) {
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
			
			List<WebElement> links = driver.findElements(By.xpath("//*[@id=\"video-title\"]"));
			
			
			for(int i=0; i<links.size() ; i++) {
				if(links.get(i).getAttribute("href") != null) {
					System.out.println(links.get(i).getAttribute("href"));
					counter = counter +1;
					if(counter == 100) {
						break;
					}
				}
			}
		}
	
				
		System.out.println(counter+ " youtube videos found");
	}
	
	
	private void DownloadVideos() {
		
	}
}
