# Automated Bug Report Mapper to Source Files.

### Intro

A tool for ranking all the source files with respect to how likely they are to contain the cause
of the bug and narrow down their search and improve productivity. The ranking is done on the basis of comparing the source code and the bug report.

* Identifying relevant files is done by finding the similarity between the **bug reports** and the **source code of the project**, **semantic similarity** between the bug report and source code files, **language** used, **file extensions**, **directory path** etc.
* After extracting relevant words from the bug report, **API calls are made to Github repository** of the specified project to fetch relevant source code files that are likely be the reason for bug based on the description of the bug report. 

---

### Getting Started

   * first download the zip file or clone the repository to your IDE
   
   * Install mongodb and create new db **abm** and  collection **bug**
   
   * Get **personal access token** from github developer settings,  if **accessing private repositories.**

     

     

     Specify your **repository information and project settings** in the **application.properties** file

     

     

     ![properities](https://github.com/varnaa/bug-mapping-using-github-API/blob/master/properities.png)

     

     

     

     After filling the project properties, execute the following code in the terminal.

     > maven clean install -X
     
     
     
     Once maven build is finished, go to your browser and launch: 
     
     > localhost:8080/new
     
     
     
     Create new bug report and save it
     
     
     
     ![create_new_bug_issue](https://github.com/varnaa/bug-mapping-using-github-API/blob/master/create_new_bug_issue.png)



Go to view bug mappings and check for the relevant source files that was mapped to the report



![file_mapping](https://github.com/varnaa/bug-mapping-using-github-API/blob/master/file_mappings.png)

