package hd.pdf2jpg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class PDF2JPGConverter {

	public void convertAll(String pdfPath, String outPath){
		try {
			PDDocument document;
			document = PDDocument.load(new File(pdfPath));
			PDFRenderer pdfRenderer = new PDFRenderer(document);
			for (int page = 0; page < document.getNumberOfPages(); ++page)
			{ 
				try {
					String filePath = Paths.get(outPath, page + "_" + new File(pdfPath).getName() + ".jpg").toString();
				    BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
				    System.out.println("Dumping page " + page + " "+ filePath);
				    ImageIOUtil.writeImage(bim, filePath, 300);
				}
				catch (Exception e) {
					System.out.print(e);					
				}
			}
			document.close();
		}
		catch (Exception e) {
			System.out.print(e);					
		}
	}

	public void convertSingle(String pdfPath, String outPath, int pageNo){
		try {
			PDDocument document;
			document = PDDocument.load(new File(pdfPath));
			PDFRenderer pdfRenderer = new PDFRenderer(document);
			for (int page = 0; page < document.getNumberOfPages(); ++page)
			{ 
				if (page!= pageNo) {
					continue;
				}
				String filePath = Paths.get(outPath, page + "_" + new File(pdfPath).getName() + ".jpg").toString();
				try {
				    BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
				    System.out.println("Dumping page " + pageNo + " "+ filePath);
				    ImageIOUtil.writeImage(bim, filePath, 300);
				}
				catch (Exception e) {
					System.out.print(e);					
				}
			}
			document.close();
		}
		catch (Exception e) {
			System.out.print(e);					
		}
	}

	public void convertMulti(String pdfPath, String outPath, String pageNos){
		try {
			PDDocument document;
			document = PDDocument.load(new File(pdfPath));
			PDFRenderer pdfRenderer = new PDFRenderer(document);
			for (String page: pageNos.split(","))
			{ 
				try {
					int pageNo = Integer.parseInt(page);
					String filePath = Paths.get(outPath, page + "_" + new File(pdfPath).getName() + ".jpg").toString();

					BufferedImage bim = pdfRenderer.renderImageWithDPI(pageNo, 300, ImageType.RGB);
				    System.out.println("Dumping page " + pageNo + " "+ filePath);
				    ImageIOUtil.writeImage(bim, filePath, 300);
				}
				catch (Exception e) {
					System.out.print(e);					
				}
			}
			document.close();
		}
		catch (Exception e) {
			System.out.print(e);					
		}
	}
}
