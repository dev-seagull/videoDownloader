package VideoDownloader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Youtube{
		
	public void collectYoutubeVideos(String userInput,WebDriver driver,Integer numberOfVideos,String destination ) throws IOException, InterruptedException {

        ArrayList<String> videoURLs = getVideosURLsOfCurrentPage(userInput,driver,numberOfVideos);
        downloadYoutubeVideos(videoURLs, destination);

    }
	
	public ArrayList<String> getVideosURLsOfCurrentPage(String userInput,WebDriver driver,Integer numberOfVideos) {

        String keyword = userInput.replace(' ', '+');
        driver.get("https://www.youtube.com/results?search_query=" + keyword);

        int videoCounts = 0;
        ArrayList<String> videoURLs = new ArrayList<String>();

        while(videoCounts != numberOfVideos) {
            List<WebElement> videoElements = driver.findElements(By.xpath("//*[@id=\"video-title\"]"));
            for (WebElement videoElement : videoElements) {
                String link = videoElement.getAttribute("href");

                if (link != null) {
                    videoURLs.add(link);
                    videoCounts++;
                }

                if (videoCounts >= numberOfVideos) {
                    break;
                }

            }
            driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
        }

        return videoURLs;
    }
	
	
	 public void downloadYoutubeVideos(ArrayList<String> urls, String destination) throws IOException, InterruptedException {
	        ProcessBuilder builder = new ProcessBuilder( "C:/Windows/System32/cmd.exe" );
	        Process process=null;
	        try {
	            process = builder.start();
	        }
	        catch (IOException err) {
	            System.out.println(err);
	        }

	        BufferedWriter p_stdin =
	                new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));


	        int n= urls.size();
	        int counter =0;
	        for (String url : urls) {
	            try {

	                p_stdin.write("yt-dlp -q --progress --ignore-errors --no-warnings  -P " + destination + " " + url);
	                p_stdin.newLine();
	                p_stdin.flush();
	            }

	            catch (IOException err) {
	                System.out.println(err);
	            }
	        }

	        try {
	            p_stdin.write("exit");
	            p_stdin.newLine();
	            p_stdin.flush();
	        }
	        catch (IOException err) {
	            System.out.println(err);
	        }

	        Scanner executionProcessBar = new Scanner( process.getInputStream());
	        while (executionProcessBar.hasNextLine())
	        {
	        	String line = executionProcessBar.nextLine();
	            Pattern pattern = Pattern.compile("([0-9]{1,3})%");
	            Matcher matcher = pattern.matcher(line);
	            if (matcher.find() && Integer.parseInt(matcher.group(1)) == 100) {
	                counter++;
	                System.out.println(counter + " out of " + n + " videos downloaded");
	            }
	            System.out.println(line);
	        }
	        executionProcessBar.close();

	    }
	
	
}
