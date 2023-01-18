package VideoDownloader;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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

	public static void main(String[] args) throws InterruptedException, IOException, ParseException {
		// TODO Auto-generated method stub
			
		    final Options options = new Options();
		    options.addOption(new Option("d","download",false,"Downloading videos(essential)"));
		    options.addOption(new Option("k","keyword",true,"Giving the keyword(essential)"));
		    
		    
		    options.addOption(new Option("p","path",true,"where you want to save the videos(optional)"));
		    options.addOption(new Option("n","number",true,"Number of videos you want to save(optional)"));
		   
		    
		    try {
		    	CommandLineParser parser = new DefaultParser();
				CommandLine cmd = parser.parse(options, args);
				
				
				String userInput = cmd.getOptionValue("k");
				String destination = cmd.getOptionValue("p");
				String numberOfVideos = cmd.getOptionValue("n");
		
				
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--headless");
				ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);
				
				
				Youtube youtube = new Youtube();
				
				
				Option[] userInputOptions  = cmd.getOptions();
				for(Option userInputOption: userInputOptions) {
					String option = userInputOption.getOpt();
					if(!options.hasOption(option)) {
						System.out.println(option + " is not recognized as a flag");
					}
				}
				
				
				String[] essentialFlags = {"d","k"}; 
				
				for(int i=0;i< essentialFlags.length;i++) {
					if(!cmd.hasOption(essentialFlags[i])) {
						System.out.println("You didn't give the "+essentialFlags[i]+ " flag");
						printHelp(options);
						System.exit(-1);
					}
				}
					
				
				if(cmd.hasOption("d")) {
					youtube.collectYoutubeVideos(userInput,chromeDriver,Integer.parseInt(numberOfVideos),destination);
				}
		    }
		    
		    
		    catch(Exception err) {
		    	
		    	System.out.println(err.getMessage());	
		    
		    }
		    		
		    
		    
		    
	
		 
		 
			
			
			
			

	}

}
