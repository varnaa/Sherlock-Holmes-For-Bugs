# Sherlock-Holmes-For-Bug

### Intro
---

  Sherlock-holmes here is a spring boot web application that helps you to quickly find files, code and commit messages that can be relevant to the issues or bug reports created while working on a project. This project is limited to git repositories only. 


---

### Getting Started

   * first download the zip file or clone the repository to your IDE
   
   * Install mongodb and create new db **name_of_ur_choice** and  collection **bug**
   
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

![file_mapping](https://github.com/varnaa/bug-mapping-using-github-API/blob/master/file_mapping.png)

