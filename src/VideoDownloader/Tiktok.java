package VideoDownloader;

import org.openqa.selenium.WebDriver;

public class Tiktok {
	public void DownloadTiktokVideos(String userInput,WebDriver driver) {
		String keyword = userInput.replace(' ', '+'); 
		driver.get("https://www.tiktok.com/tag/" + keyword);
		 
	}
}
