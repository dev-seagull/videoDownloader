package VideoDownloader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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
            if (videoElements.isEmpty()) {
                break;
            }

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
        
        driver.close();
        return videoURLs;
    }
	
	
	 public void downloadYoutubeVideos(ArrayList<String> urls, String destination) throws IOException, InterruptedException {
	
	       
	        int n= urls.size();
	        int counter =0;
	        for (String url : urls) {
	        	
	        	try {
	       	        
	        		
	        		Process process = Runtime.getRuntime().exec("yt-dlp -q --progress --ignore-errors --no-warnings  -P " + destination + " " + url);
	    			
	    			BufferedReader Input = new BufferedReader(new InputStreamReader(process.getInputStream()));
	    			BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));
	    			
	    			String line = null;
	    			while ((line = Input.readLine()) != null) {   
	    			    Pattern pattern = Pattern.compile("([0-9]{1,3})%");
	    			    Matcher matcher = pattern.matcher(line);
	    			    if (matcher.find() && Integer.parseInt(matcher.group(1)) == 100) {
	  	    	            counter++;
	  	    	            System.out.println(counter + " out of " + n + " videos downloaded");
	  	    	         }
	    			   
	    			    
	    			    System.out.println(line);
	    			    
	    			}
	        		
	       	                   
	                
	    			while ((line = err.readLine()) != null) {
	                    System.out.println("Here is the standard error of the command (if any):\n");
	                    System.out.println(line);
	                }
	                
	                
	                
	                
	                
	            } catch (IOException err) {
	                System.out.println(err);
	            }
	            
	  
	   
	  

	    }
	
	 }
}
