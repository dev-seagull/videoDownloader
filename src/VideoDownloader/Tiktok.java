package VideoDownloader;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Tiktok {
	public void DownloadTiktokVideos(String userInput,WebDriver driver,Integer numberOfVideos) {
		String keyword = userInput.replace(' ', '+'); 
		driver.get("https://www.tiktok.com/tag/" + keyword);
		 
		int counter=0;
		ArrayList<String> links = new ArrayList<String>();
		while(counter<numberOfVideos) {
			List<WebElement> elements = driver.findElements(By.xpath("//*[@id='app']/div[2]/div[2]/div/div[2]/div/div[\"+ String.valueOf(i) +\"]/div[1]/div/div/a"));
			
			if(counter ==numberOfVideos) {
				break;
			}
			
			else{
				for(int i=0;i<elements.size();i++) {
					String link = elements.get(i).getAttribute("href");
					links.add(link);
					System.out.println(link);
					counter++;
					if(counter == numberOfVideos) {
						break;
					}
				}
				driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
			}
			
			

		}
	}
}
