package VideoDownloader;

import java.io.IOException;
import java.io.PrintWriter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Main {


	
	public static void printHelp(Options options) {
	    HelpFormatter helpFormatter = new HelpFormatter();
	    PrintWriter pw = new PrintWriter(System.out);
	    helpFormatter.printUsage(pw,100,"VideoDownloader [options]");
	    pw.println("For example:VideoDownloader -k chest workout");
	    pw.println("For getting more information in details use -h: VideDownloader -h");
	    helpFormatter.printOptions(pw, 100, options, 2, 5);
	    pw.close();
	  }
	
	
	public static void downloadVideos(String keyword,String path,String numberOfVideos) throws NumberFormatException, IOException, InterruptedException {
		
	

		
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
    
		ChromeOptions chromeOptions = new ChromeOptions();
	    chromeOptions.addArguments("--remote-allow-origins=*");
		chromeOptions.addArguments("--headless");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);
		System.setProperty("webdriver.chrome.driver","G://Selenium jar and drivers//chromedriver/chromedriver.exe");
		
		
		Youtube youtube = new Youtube();
		youtube.collectYoutubeVideos(keyword, chromeDriver, Integer.parseInt(numberOfVideos), path);
	}
	
	

	public static void main(String[] args) throws InterruptedException, IOException, ParseException {
		// TODO Auto-generated method stub
			
		    final Options options = new Options();
		    options.addOption(Option.builder("k").longOpt("keyword").hasArg().desc("Keyword for search(essential)").build());
		    
		    
		    options.addOption(Option.builder("p").longOpt("path").hasArg().desc("path to save videos(optional, default value is set to C:\\\\VideoDownloader)").build());
		    options.addOption(Option.builder("n").longOpt("number").hasArg().desc("Number of videos you want to download(optional, dafult value is set to 5)").build());
		    options.addOption(Option.builder("h").longOpt("help").desc("Print help").build());
		    
		    
		    
		    
		    try {
		    	CommandLineParser parser = new DefaultParser();
				CommandLine cmd = parser.parse(options, args);
				
				
				String userInput = cmd.getOptionValue("k");
				
				String path = cmd.getOptionValue("p");
				if(path == null) {
					path = "C:\\VideoDownloader";
				}
				
				String numberOfVideos = cmd.getOptionValue("n");
				if(numberOfVideos == null) {
					numberOfVideos = "5";
				}
				
				
				if(cmd.hasOption("h")) {
					printHelp(options);
					System.exit(-1);
				}
				
				String[] essentialFlags = {"k"}; 
				for(String essentialFlag: essentialFlags) {
					if(!cmd.hasOption(essentialFlag)) {
						System.out.println("The flag "+essentialFlag+ " was not given");
						printHelp(options);
						System.exit(-1);
					}
				}
				
				downloadVideos(userInput, path, numberOfVideos);
				
				
		    } catch(Exception err) {
		    	
		    	System.out.println(err.getMessage());	
		    
		    }
		    		
	}

}
