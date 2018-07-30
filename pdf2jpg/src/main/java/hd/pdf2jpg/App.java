package hd.pdf2jpg;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.json.JSONObject;

public class App 
{
    public static void main( String[] args ) throws InvalidPasswordException, IOException
    {	
		JSONObject jsonString = new JSONObject();
    	try{
    		Options options = new Options();

            Option bulk = new Option("b", "bulk", false, "Mode of execution is this is selected directory of pdf will be processed instead of single PDF");
            bulk.setRequired(false);
            options.addOption(bulk);

            Option input = new Option("i", "input", true, "input path(pdf location), for mode(BULK) enter dirpath");
            input.setRequired(true);
            options.addOption(input);

            Option output = new Option("o", "output", true, "output directory");
            output.setRequired(true);
            options.addOption(output);

            Option pages = new Option("p", "pages", true, "Pages to convert. Comma seprated page list Eg. 1,2,3,4,8 or ALL to convert all pages");
            pages.setRequired(true);
            options.addOption(pages);

            Option threads = new Option("t", "threads", true, "Default 2 [optional BULK mode only] number of threads to use");
            threads.setRequired(false);
            options.addOption(threads);

            CommandLineParser parser = new DefaultParser();
            HelpFormatter formatter = new HelpFormatter();
            CommandLine cmd = null;


            try {
            	cmd = parser.parse(options, args);
            
            } 
            catch (ParseException e) {
                System.out.println(e.getMessage());
                formatter.printHelp("utility-name", options);
                System.exit(1);
            }

			String bulk_ = cmd.getOptionValue("bulk");
			String inputPath = cmd.getOptionValue("input");
            String outputPath = cmd.getOptionValue("output");
			String pages_ = cmd.getOptionValue("pages");
			String threads_ = cmd.getOptionValue("threads");
			
			File directory = new File(outputPath);
		    if (! directory.exists()){
		        directory.mkdirs();
		    }

			if (bulk_ != null){
				System.out.println("BULK Mode using Java native implementation | Under implementation");
			}
			else {
				String pdfOutputPath = Paths.get(outputPath, new File(inputPath).getName()).toString();
				System.out.println(pdfOutputPath);
				jsonString.put(inputPath, pdfOutputPath);
				directory = new File(pdfOutputPath);

				if (! directory.exists()){
			        directory.mkdirs();
			    }
				
				PDF2JPGConverter converter = new PDF2JPGConverter();
				if (pages_.equals("ALL")){
					converter.convertAll(inputPath, pdfOutputPath);
					
				}
				else {
					converter.convertMulti(inputPath, pdfOutputPath, pages_);
				}
			}
    	}
    	catch (Exception err){
    		System.out.println("ERROR:"+ err.toString());
    		help();
    	}
    	System.out.println("#################################");
    	System.out.println(jsonString.toString());
    }

    public static void help(){
		System.out.println("==============================================================");
		System.out.println("ERROR: Invalid Arguments, call the jar as below");
		System.out.println("java -jar jarPath inputPath outDir ALL|SINGLE|MULTI [pageNo]");			
		System.out.println("java -jar jarPath inputPath outDir ALL");			
		System.out.println("java -jar jarPath inputPath outDir SINGLE 1");			
		System.out.println("java -jar jarPath inputPath outDir MULTI 2,3,4");
		System.out.println("==============================================================");    	
    }
}
