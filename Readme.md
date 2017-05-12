Steps to run project :
1. Install java8 (jdk) on your machine 
2. On command line run :    
     java -version  
   output should be similar to : Java(TM) SE Runtime Environment (build 1.8.0_111-b14)
2. Install maven  and make sure you can access mvn command from command line 
   To check maven installation run following   
    mvn --version  
    output should be similar to : Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-10T22:11:47+05:30)
3. go to project directory dropwizard-example on command line ( cd PATH_TO_dropwizard-example_dir)  
    e.g. cd /Users/jitendragangwar/downloads/dropwizard-example
4. Type  following commands on terminal then hit enter    
   i)  mvn clean compile package  
   ii) java -jar target/dropwizard-example-1.0-SNAPSHOT.jar server src/main/resources/local/config.yml 

5. This project exposes following 2 apis   
    1) /demo/name : A GET api , it simply returns a map , which contains application name  
    using curl you can hit this api as follows :   
        curl -X GET http://localhost:8080/demo/name  
    Response :: {"name":"Dropwizard Basic Example"}
    2) /demo/dummy  : A POST api , it takes input an object consisting of firstName and lastname in json format  
        and returns same object as json  
     usage :   
        curl -X POST -H "Content-Type: application/json"  -d '{"firstName":"Java","lastName":"Server"}' 'http://localhost:8080/demo/dummy'  
     Response : {"firstName":"Java","lastName":"Server"}


Steps to develop project :
1) Install IntellijIdea community edition or eclipse ( I prefer IntelliJ Idea )
2) Click on File and then click on open , browse to example project dir ,select this dir, then open it
3) You might need to set up jdk path in intellij ( it might show you this message , just click on it and then select your java installation path)
4) Now develop your code 
5) Application start class is DropApplication.java . Here you will write some code in run method for exposing cache apis   
6) You can also run project using Intellij by creating a main configuration , detailes steps are given in intellij idea zip
7) Some useful shortcut for intellij -> Alt + Enter : auto complete and suggestion  
                                     -> Cltr + left click : on class Name or function name : to see it's definition     
                                     -> Cltr+Shift+O / Cltr+Shift+N : type class name and open it   
      
Visit Url : https://medium.com/@jitrigal/dropwizard-setup-on-intellij-idea-d73d527d9208
    
