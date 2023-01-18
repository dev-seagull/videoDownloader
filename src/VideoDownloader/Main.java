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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {
	
	public static void printHelp(Options options) {
	    HelpFormatter helpFormatter = new HelpFormatter();
	    PrintWriter pw = new PrintWriter(System.out);
	    helpFormatter.printUsage(pw,100,"java -jar VideoDownloader.jar -d [options]	");
	    pw.println("Instruction: these options are necessary: -d, -k, -p and -n");
	    pw.println("For example:java -jar VideoDownloader -d -k chest workout -p G:\\grabvideotesting -n 3");
	    helpFormatter.printOptions(pw, 100, options, 2, 5);
	    pw.close();
	  }
	
	
	public static void downloadVideos(String keyword,String path,String numberOfVideos) throws NumberFormatException, IOException, InterruptedException {
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);
		
		
		Youtube youtube = new Youtube();
		youtube.collectYoutubeVideos(keyword, chromeDriver, Integer.parseInt(numberOfVideos), path);
	}
	
	

	public static void main(String[] args) throws InterruptedException, IOException, ParseException {
		// TODO Auto-generated method stub
			
		    final Options options = new Options();
		    options.addOption(Option.builder("d").longOpt("download").desc("Download videos(essential)").build());
		    options.addOption(Option.builder("k").longOpt("keyword").hasArg().desc("Keyword for search(essential)").build());
		    
		    
		    options.addOption(Option.builder("p").longOpt("path").hasArg().desc("path to save videos(optional)").build());
		    options.addOption(Option.builder("n").longOpt("number").hasArg().desc("Number of videos you want to download(optional)").build());
		    options.addOption(Option.builder("h").longOpt("help").desc("Print help").build());
		    
		    
		    try {
		    	CommandLineParser parser = new DefaultParser();
				CommandLine cmd = parser.parse(options, args);
				
				
				String userInput = cmd.getOptionValue("k");
				String path = cmd.getOptionValue("p");
				String numberOfVideos = cmd.getOptionValue("n");
					
				
				if(cmd.hasOption("h")) {
					printHelp(options);
					System.exit(-1);
				}
				
				String[] essentialFlags = {"d","k"}; 
				for(String essentialFlag: essentialFlags) {
					if(!cmd.hasOption(essentialFlag)) {
						System.out.println("You didn't give the "+essentialFlag+ " flag");
						printHelp(options);
						System.exit(-1);
					}
				}
					
				
				if(cmd.hasOption("d")) {
					downloadVideos(userInput,path,numberOfVideos);
				}
				
				
		    } catch(Exception err) {
		    	
		    	System.out.println(err.getMessage());	
		    
		    }
		    		
	}

}
