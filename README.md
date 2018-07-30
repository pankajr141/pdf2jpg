
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

```python
from pdf2jpg import pdf2jpg
inputpath = r"D:\inputdir\pdf1.pdf"
outputpath = r"D:\outputdir"
# To convert single page
result = pdf2jpg.convert_pdf2jpg(inputpath, outputpath, pages="1")
print(result)

# To convert multiple pages
result = pdf2jpg.convert_pdf2jpg(inputpath, outputpath, pages="1,0,3")
print(result)

# to convert all pages
result = pdf2jpg.convert_pdf2jpg(inputpath, outputpath, pages="ALL")
print(result)
```

### Directly through jar - data/pdf2jpg.jar
To use the jar just type below commands which will work

```Python
To convert single pdf page to image [Eg, below converting 3rd page]
$ java -jar data/pdf2jpg.jar -i path_to_pdf -o output_directory -p 2

To convert Multiple pdf pages to image 
$ java -jar data/pdf2jpg.jar -i path_to_pdf -o output_directory -p 0,1,2,3

To convert ALL pdf pages to image
$ java -jar data/pdf2jpg.jar -i path_to_pdf -o output_directory -p ALL
```

**OLD Jar usage - data/pdf2jpg_noargs.jar**  [Deprecated and will be removed in later revision]

```Python
To convert single pdf page to image
$ java -jar data/pdf2jpg_noargs.jar path_to_pdf output_directory SINGLE 1`

To convert Multiple pdf pages to image
$ java -jar data/pdf2jpg_noargs.jar path_to_pdf output_directory MULTI 1,2,4`

To convert ALL pdf pages to image
$ java -jar data/pdf2jpg_noargs.jar path_to_pdf output_directory ALL
```


## To do
* Bulk Model implementation in java, to convert directory instead of single pdf [yet to decide if to use multithrreading or multiprocesing]
* Python bindings
