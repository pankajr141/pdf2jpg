'''
Created on Jul 30, 2018

@author: 703188429
'''
import os
import subprocess
import ast
import shutil
import commands
import platform

def convert_pdf2jpg_single(jarPath, inputpath, outputpath, pages):
    cmd = 'java -jar %s -i "%s" -o "%s" -p %s' % (jarPath, inputpath, outputpath, pages)    
    outputpdfdir = os.path.join(outputpath, os.path.basename(inputpath))
    if os.path.exists(outputpdfdir):
        shutil.rmtree(outputpdfdir)
    system = platform.system()
    if system == "Linux":
        output = commands.getoutput(cmd)
    else:
        output = subprocess.check_output(cmd)
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
    return [result]
 
def convert_pdf2jpg(inputpath, outputpath, pages="ALL", bulk=False, jobs=2):
    pages = pages.split(",")
    pages = map(lambda x: x.strip(), pages)
    pages = ",".join(pages)
    jarPath = os.path.join(os.path.dirname(os.path.realpath(__file__)), r"pdf2jpg.jar")

    if not bulk:
        return convert_pdf2jpg_single(jarPath, inputpath, outputpath, pages=pages)

if __name__ == "__main__":
    inputpath = r"D:\pharma\Dataset\Pharma\legal_US_4.pdf"
    outputpath = r"D:\Working Folder\pd"
    result = convert_pdf2jpg(inputpath, outputpath, pages="1,0,3", bulk=False, jobs=2)
    print(result)

