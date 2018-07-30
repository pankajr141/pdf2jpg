'''
Created on Jul 30, 2018

@author: 703188429
'''
import setuptools
from glob import glob

with open("README.md", "r") as fh:
    long_description = fh.read()

setuptools.setup(
    name="pdf2jpg",
    version="0.0.3",
    author="Pankaj Rawat",
    author_email="pankajr141@gmail.com",
    description="Wrapper to convert PDF files into jpg",
    long_description=long_description,
    long_description_content_type="text/markdown",
    url="https://github.com/pankajr141/pdf2jpg",
    packages=setuptools.find_packages(),
#     data_files=[
#         ('images', glob('pdf2jpg/*.jar')),
#     ],
    package_data={'': ['pdf2jpg/pdf2jpg.jar']},
    include_package_data=True,
    classifiers=(
        "Programming Language :: Python :: 3",
        "License :: OSI Approved :: MIT License",
        "Operating System :: OS Independent",
    ),
)