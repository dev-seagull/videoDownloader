package VideoDownloader;

import org.openqa.selenium.WebDriver;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			Chrome chrome = new Chrome();
			WebDriver chromeDriver = chrome.CreateChromeDriver();
			Youtube youtube = new Youtube();
			youtube.SearchAndGetVideos("https://www.youtube.com/results?search_query=hi",chromeDriver);
	}

}
