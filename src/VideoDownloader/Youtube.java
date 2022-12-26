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
	
	public void getYoutubeVideos(String userInput,WebDriver driver,Integer numberOfVideos) throws InterruptedException{
		String keyword = userInput.replace(' ', '+');  
		
		driver.get("https://www.youtube.com/results?search_query="+keyword);
		
		int counter=0;
		while(counter<numberOfVideos) {
			
			List<WebElement> links = driver.findElements(By.xpath("//*[@id=\"video-title\"]"));
			
			
			for(int i=0; i<links.size() ; i++) {
				if(links.get(i).getAttribute("href") != null) {
					counter = counter +1;
					if(counter == numberOfVideos+1) {
						break;
					}
					else {
						System.out.println(links.get(i).getAttribute("href"));
					}
				}
			}
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
		}
	
				
		System.out.println(counter-1 + " youtube videos found");
	}
	
	
	private void DownloadYoutubeVideos() {
		
	}
}
