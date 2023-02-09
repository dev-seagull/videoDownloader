package VideoDownloader;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Tiktok {
	public void collectTiktokVideos(String userInput,WebDriver driver,Integer numberOfVideos,String destination ) throws IOException, InterruptedException {
		 
		ArrayList<String> videoURLs = getVideosURLsOfTagPage(userInput,driver,numberOfVideos);
		
		
		downloadTikTokVideos(videoURLs,destination);
		
	}
	
	
	public ArrayList<String> getVideosURLsOfTagPage(String userInput,WebDriver driver,Integer numberOfVideos) {
		String keyword = userInput.replace(' ', '+'); 
		driver.get("https://www.tiktok.com/tag/" + keyword);
		
		int videoCounts = 0;
		ArrayList<String> videoURLs = new ArrayList<String>();
		
		while(videoCounts != numberOfVideos) {
			List<WebElement> videoElements = driver.findElements(By.xpath("//*[@id='app']/div[2]/div[2]/div/div[2]/div/div[\"+ String.valueOf(i) +\"]/div[1]/div/div/a"));
			for(WebElement videoElement: videoElements) {
				String link = videoElement.getAttribute("href");
				videoURLs.add(link);
				videoCounts++;
				
				if(videoCounts >= numberOfVideos) {
					break;
				}
				
			}
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
		}
			
		return videoURLs;
	}
	
	
	public void downloadTikTokVideos(ArrayList<String> urls,String destination) throws IOException, InterruptedException {
		for(String url: urls) {
			String[] command = 
						{
							"cmd"		
						};
			Process process = Runtime.getRuntime().exec(command);
			PrintWriter stdin = new PrintWriter(process.getOutputStream());
			stdin.println("yt-dlp -q --progress --ignore-errors --no-warnings  -P "+destination+ " " +url);
			stdin.close();
			process.waitFor();
		}
	}
	
}
