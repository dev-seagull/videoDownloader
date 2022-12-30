package VideoDownloader;

import java.io.IOException;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
			Chrome chrome = new Chrome();
			WebDriver chromeDriver = chrome.CreateChromeDriver();
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("What do you want to search?: ");
			String userInput = scanner.nextLine();
			System.out.println("How many videos do you want? ");
			Integer numberOfVideos = Integer.parseInt(scanner.next()); 
			System.out.println("Where do you want to save? ");
			scanner.nextLine();
			String destination = scanner.nextLine();
 			
			
			Youtube youtube = new Youtube();
			youtube.DownloadYoutubeVideos(userInput,chromeDriver,numberOfVideos,destination);
			
			//Instagram instagram = new Instagram();
			//instagram.getInstagramVideos("hi",chromeDriver);
	}

}
