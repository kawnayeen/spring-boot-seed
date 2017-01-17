Spring Boot Seed Project
==========================


This is a seed project for developing RESTful services using Spring Boot



Features
--------
* Spring Security
* Custom Basic Authentication Provider
* JWT Token Authentication Filter
* API Rate limiting
* Spring Data
* Restful Webservice
* Exception Handling
* Spring AOP (Aspect Oriented Programming)
* In-Memory Database
* Transactions Management
* Cache Management
* Redis
* Unit Testing
* Integration Testing
* Spring REST Docs

Api Docs
---------
Please check [api documentation](http://htmlpreview.github.com/?https://github.com/kawnayeen/spring-boot-seed/blob/release/target/generated-docs/demo.html) first.

How to Run?
-----------
As this application has embedded tomcat server, embedded redis 
& work with in-memory h2 database, so you already need to have 
the following dependencies
  * JDK 8 or later
  * Maven 3 or later

To run this application, open terminal on the project root directory 
and type the following

```
mvn spring-boot:run
```

If you want to stop the server, then enter ```ctlr-C``` 

To generate documentation using rest docs, first run the test code of 
```LoggingResourceTest.java``` & make sure all necessary ascii docs snippets
are available at ```target/generated-snippets``` directory. 
 
Then open the terminal at project root directory and run the following command

```
mvn prepare-package
```

You will find the documentation at ```target/generated-docs``` directory

License
--------
Licensed under the Apache License, Version 2.0
 
 
 
Contributor
-----------
Muhammad Kamarul Kawnayeen ([@kawnayen08](https://github.com/kawnayeen08))
 
