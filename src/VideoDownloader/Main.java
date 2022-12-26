package VideoDownloader;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
			Chrome chrome = new Chrome();
			WebDriver chromeDriver = chrome.CreateChromeDriver();
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("What do you want to search?: ");
			String userInput = scanner.nextLine();
			System.out.println("How many videos do you want? ");
			Integer numberOfVideos = Integer.parseInt(scanner.next()); 
 			
			
			Youtube youtube = new Youtube();
			youtube.getYoutubeVideos(userInput,chromeDriver,numberOfVideos);
			
			//Instagram instagram = new Instagram();
			//instagram.getInstagramVideos("hi",chromeDriver);
	}

}
