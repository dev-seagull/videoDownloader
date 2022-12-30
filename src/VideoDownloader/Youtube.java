package VideoDownloader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Youtube{
	
	public void DownloadYoutubeVideos(String userInput,WebDriver driver,Integer numberOfVideos,String destination) throws InterruptedException, IOException{
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
						String[] command = 
							{
								"cmd"		
							};
							
							Process process = Runtime.getRuntime().exec(command);
							PrintWriter stdin = new PrintWriter(process.getOutputStream());
							String url = links.get(i).getAttribute("href");
							stdin.println("yt-dlp -q --progress --ignore-errors --no-warnings  -P "+destination+ " " +url);
							stdin.close();
							process.waitFor();
					}
				}
			}
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
			
		}
	
				
		System.out.println(counter-1 + " youtube videos found");
	}
	
	
	
}
