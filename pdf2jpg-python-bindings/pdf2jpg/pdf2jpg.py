'''
Created on Jul 30, 2018

@author: 703188429
'''
import os
import subprocess
import ast
import shutil
import platform
import img2pdf

def __convert_pdf2jpg_single(jarPath, inputpath, outputpath, pages):
    try:
        cmd = 'java -jar %s -i "%s" -o "%s" -p %s' % (jarPath, inputpath, outputpath, pages)    
        outputpdfdir = os.path.join(outputpath, os.path.basename(inputpath))
        if os.path.exists(outputpdfdir):
            shutil.rmtree(outputpdfdir)
    
        system = platform.system()
        if system == "Linux":
            cmd = ["java", "-jar", jarPath, "-i", inputpath, "-o", outputpath, "-p", pages]
            output = subprocess.check_output(cmd)
        else:
            output = subprocess.check_output(cmd)
        
        output = output.decode()
        output = output.split("#################################")[1].strip()
    
        output = ast.literal_eval(output)
        outputpdfdir = output[inputpath]
        
        outputFiles = map(lambda x: os.path.join(outputpdfdir, x), os.listdir(outputpdfdir))
        outputFiles = sorted(outputFiles, key=lambda x: os.path.basename(x).split("_")  [0])   
        
        result = {
            'cmd': cmd,
            'input_path': inputpath,
            'output_pdfpath': outputpdfdir,
            'output_jpgfiles': outputFiles
        }
    except Exception as err:
        print(err)
        return False
    return [result]
 
"""
Function convert pdf into jpg files 

Arguments:
==========

inputpath  - Input pdf path
outputpath - outputdirectory
pages      - Pages to be converted Eg "ALL" | "1,3,4" | "2,6"

"""
def convert_pdf2jpg(inputpath, outputpath, pages="ALL"):
    pages = pages.split(",")
    pages = map(lambda x: x.strip(), pages)
    pages = ",".join(pages)
    jarPath = os.path.join(os.path.dirname(os.path.realpath(__file__)), r"pdf2jpg.jar")
    return __convert_pdf2jpg_single(jarPath, inputpath, outputpath, pages=pages)

"""
Function convert pdf into pdf of images, in short convert a OCR PDF into not non-OCR pdf
Arguments:
==========

inputpath  - Input pdf path
outputpath - outputdirectory
pages      - Pages to be converted Eg "ALL" | "1,3,4" | "2,6"

"""
def convert_pdf2imgpdf(inputpath, outputpath):
    try:
        jpgOutputDir = "tmp_pdf2jpg"
        if os.path.exists(jpgOutputDir):
            shutil.rmtree(jpgOutputDir)

        output = convert_pdf2jpg(inputpath, jpgOutputDir, pages="ALL")
        if not output:
            print("Unable to convert PDF into images")
            return False
        
        outputjpgfiles = output[0]['output_jpgfiles']
        print(outputjpgfiles)
        
        outputdir = os.path.dirname(outputpath)
        if not os.path.exists(outputdir):
            os.makedirs(outputdir)

        with open(outputpath, "wb") as f:
            f.write(img2pdf.convert(outputjpgfiles))   
        shutil.rmtree(jpgOutputDir)
    except Exception, err:
        print(err)
        return False
    return True

if __name__ == "__main__":
    inputpath = r"D:\pharma\Dataset\Pharma\legal_US_4.pdf"
    outputpath = r"D:\Working Folder\pd\fileformat.pdf"
    #result = convert_pdf2jpg(inputpath, outputpath, pages="1,0,3")
    #print(result)
    result = convert_pdf2imgpdf(inputpath, outputpath)
    print(result)
