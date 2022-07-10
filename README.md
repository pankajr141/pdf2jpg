
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

we only support python3

`pip3 install pdf2jpg`

also make sure that you have java installed in system, just check if entering java in terminal is working and not throwing error

The video contains demo of installation and usage
[![Demo Video](https://github.com/pankajr141/collection/raw/main/others/pdf2jpg_embed1.png)](https://www.youtube.com/watch?v=SL3WiUpvZ5c&list=UUplf_LWNn0a9ubnKCZ-95YQ&index=12)

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

output results
```
[{   
    'cmd': 'java -jar D:\\pdf2jpg-bindings\\pdf2jpg\\pdf2jpg.jar -i "D:\inputdir\pdf1.pdf" -o "D:\outputdir" -d 300 -p 0,1,2,3',
    'input_path': 'D:\inputdir\pdf1.pdf',
    'output_jpgfiles': [   
			'D:\outputdir\\pdf1.pdf\\0_pdf1.pdf.jpg',
                        'D:\outputdir\\pdf1.pdf\\1_pdf1.pdf.jpg',
                        'D:\outputdir\\pdf1.pdf\\2_pdf1.pdf.jpg',
                        'D:\outputdir\\pdf1.pdf\\3_pdf1.pdf.jpg'
			],
    'output_pdfpath': 'D:\outputdir\\pdf1.pdf'
}]
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
