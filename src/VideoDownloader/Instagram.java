package VideoDownloader;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Scanner;


public class Instagram {
	
	
    Scanner input = new Scanner(System.in);
	
	Chrome chrome = new Chrome();
	
	
	public void Login(WebDriver driver) {
	
		driver.get("http://www.instagram.com");
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		
		
		WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='username']")));
		WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='password']")));
		
		
		System.out.print("Enter your username:");
		String usernameInput = input.nextLine();

		System.out.print("Enter your Password:");
		String passwordInput = input.nextLine();
		
		username.clear();
		username.sendKeys(usernameInput);
		driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
		
		password.clear();
		password.sendKeys(passwordInput);
		driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
		
		WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit'"));
		loginButton.click();
		driver.manage().timeouts().implicitlyWait(12,TimeUnit.SECONDS);
		
		
		//WebElement not_now = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/div/div/div/div[1]/div[1]/div[2]/section/main/div/div/div/div/button"));
		//not_now.click();
		//driver.manage().timeouts().implicitlyWait(13,TimeUnit.SECONDS);
	}
	
	
	public void SearchAndGetVideos(String url, WebDriver driver) {
		
	}
	
}
