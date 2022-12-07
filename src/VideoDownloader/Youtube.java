package VideoDownloader;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Youtube{
	
	Chrome chrome = new Chrome();
	
	public void SearchAndGetVideos(String url, WebDriver driver) {
		List<WebElement> links =  chrome.FindVideoLinksByXPATH(url, driver);

		for(int i=0; i<links.size() ; i++) {
			System.out.println(links.get(i).getAttribute("href"));
		}
	}
	
	
	private void DownloadVideos() {
		
	}
}
