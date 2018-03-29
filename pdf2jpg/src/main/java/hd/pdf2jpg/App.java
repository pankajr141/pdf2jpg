package hd.pdf2jpg;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

public class App 
{
    public static void main( String[] args ) throws InvalidPasswordException, IOException
    {	
    	try{
			String pdfPath = args[0];
			String outPath = args[1];
			String mode = args[2];
		    File directory = new File(outPath);
		    if (! directory.exists()){
		        directory.mkdirs();
		    }
			System.out.println(pdfPath);
			PDF2JPGConverter converter = new PDF2JPGConverter();
			if (mode.equals("ALL")){
				converter.convertAll(pdfPath, outPath);
			}
			else if (mode.equals("MULTI")){
				String pageNos = args[3];			
				converter.convertMulti(pdfPath, outPath, pageNos);
			}
			else if (mode.equals("SINGLE")){
				int pageNo = Integer.parseInt(args[3]);			
				converter.convertSingle(pdfPath, outPath, pageNo);
			}
			else{
	    		help();
			}
    	}
    	catch (Exception err){
    		System.out.println("ERROR:"+ err.toString());
    		help();
    	}
    }
    public static void help(){
		System.out.println("==============================================================");
		System.out.println("ERROR: Invalid Arguments, call the jar as below");
		System.out.println("java -jar jarPath pdfPath outDir ALL|SINGLE|MULTI [pageNo]");			
		System.out.println("java -jar jarPath pdfPath outDir ALL");			
		System.out.println("java -jar jarPath pdfPath outDir SINGLE 1");			
		System.out.println("java -jar jarPath pdfPath outDir MULTI 2,3,4");
		System.out.println("==============================================================");    	
    }
}
