package VideoDownloader;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Instagram {
	
	
    Scanner input = new Scanner(System.in);
	
	Chrome chrome = new Chrome();
	
	
	public void getInstagramVideos(String keyword,WebDriver driver) throws InterruptedException {
	
		driver.get("https://www.google.com");
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		
		WebElement searchBar = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
		searchBar.sendKeys(keyword + " site:www.instagram.com");
		searchBar.submit();
		
		WebElement videosTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hdtb-msb\"]/div[1]/div/div[2]/a")));
		videosTab.click();
		
		
		Thread.sleep(6000);
		
		ArrayList<WebElement> links = new ArrayList<WebElement>();
		
		for(int i=1;i<10;i++) {
			WebElement link = driver.findElement(By.xpath("//*[@id='rso']/div[" + String.valueOf(i) + "]/div/div/div/video-voyager/div/div[1]/a"));
			links.add(link);
		}
		                                                  
		
		int counter =0;
		for(int i=0;i<links.size();i++) {
			System.out.println(links.get(i).getAttribute("href"));
			counter = counter +1;
		}
		System.out.println(counter+"videos found");
		
		
		int pageCounter = 0;
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
		WebElement page2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"botstuff\"]/div/div[2]/table/tbody/tr/td[3]/a")));
		driver.get(page2.getAttribute("href"));
	
	}
	
	
}
