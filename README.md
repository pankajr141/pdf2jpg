
## Building jar file from source.

To build the package maven is used, by default pdfbox does not include converted for certain jpg images.
To add support include the jar file provided in data/dependency path of project in your classpath and then maven compile.

**Dependency Jar location -** pdf2jpg/data/dependency/jbig2-imageio-3.0.0-SNAPSHOT.jar

Below is the entry in pom.xml for this jar file.

```xml

	<dependency> 
	    <groupId>org.apache.pdfbox</groupId>
	    <artifactId>jbig2-imageio</artifactId>
	    <version>3.0.0-SNAPSHOT</version>
	    <type>jar</type> <!-- Meaning it is picking this artifact from a jar file, add this jar to classpath-->
	</dependency>
```

After adding above in pom.xml add the above jar in classpath and your are good to go.

## Installation

`pip install pdf2jpg`

## Usage 
The utility can be executed in two ways

### Python bindings

Convertion of PDF into jpgfiles
```python
from pdf2jpg import pdf2jpg
inputpath = r"D:\inputdir\pdf1.pdf"
outputpath = r"D:\outputdir"
# To convert single page
result = pdf2jpg.convert_pdf2jpg(inputpath, outputpath, dpi=300, pages="1")
print(result)

# To convert multiple pages
result = pdf2jpg.convert_pdf2jpg(inputpath, outputpath, dpi=300, pages="1,0,3")
print(result)

# to convert all pages
result = pdf2jpg.convert_pdf2jpg(inputpath, outputpath, dpi=300, pages="ALL")
print(result)
```

Convertion of Readable PDF into PDF of scanned images, in converted PDF user will not be able to select any text.
```python
from pdf2jpg import pdf2jpg
inputpath = r"D:\inputdir\pdf1.pdf"
outputpath = r"D:\outputdir\pdf1.pdf"

# To convert pdf to imgpdf
result = pdf2jpg.convert_pdf2imgpdf(inputpath, outputpath, dpi=300)
print(result)
```

### Directly through jar - data/pdf2jpg.jar
To use the jar just type below commands which will work

```Python
To convert single pdf page to image [Eg, below converting 3rd page]
$ java -jar data/pdf2jpg.jar -i path_to_pdf -o output_directory -d 300 -p 2

To convert Multiple pdf pages to image 
$ java -jar data/pdf2jpg.jar -i path_to_pdf -o output_directory -d 300 -p 0,1,2,3

To convert ALL pdf pages to image
$ java -jar data/pdf2jpg.jar -i path_to_pdf -o output_directory -d 300 -p ALL
```

## To do
* Bulk Model implementation in java, to convert directory instead of single pdf [yet to decide if to use multithrreading or multiprocesing]
* Python bindings
