# Wiremock API Automation using Rest Assured 

This project is a Sample and Demonstration of WireMock API automation framework using rest-assured.


## Overview of Wiremock:

WireMock is a library for stubbing and mocking web services. 
It constructs a HTTP server that we could connect to as we would to an actual web service.

Benefits of using mocks are: quick tests, cheap, fast to implement, deterministic tests.

What Is Stubbing?
Stubbing is a technique that allows us to configure the HTTP response that is returned by our WireMock server when it receives a specific HTTP request. We can stub HTTP requests with WireMock by using the static givenThat() method of the WireMock class. 
When we invoke this method, we have to create a new MappingBuilder object and pass this object to the invoked method as a method parameter. 

The MappingBuilder object has two responsibilities:

It identifies the HTTP request that triggers the configured HTTP response. We can identify the HTTP request by using WireMock’s request matching support.
It configures the returned HTTP response. We can configure the returned HTTP response by using the willReturn() method of the MappingBuilder interface. When we invoke this method, we have to create a new ResponseDefinitionBuilder object and pass this object to the invoked method as a method parameter.

Rest Assured : Rest-Assured is a Java-based library that is used to test RESTful Web Services. 

Tools & libraries:
==================

- org.springframework.boot 
- org.apache.maven.plugins
- com.fasterxml.jackson
- io.rest-assured
- org.testng
- JDK 8.0
- Swagger-UI


Setup and Run Procedure:
=======================

Run the server

	- Once the clone & setup has done run the “io.swagger” package as “Java Application”.
	- You can view the api documentation in swagger-ui by pointing to http://localhost:8080/v2/swagger-ui.html

Run the test suite

	- Once the server is up and running run your tests by executing the “EmployeeSuite.xml” file
	- Test Report can be found in “test-output” directory “emailable-report.html” file


Automation Structure:

 +---src
    |   +---main
    |   |
    |   \---test
    |       +---java
    |       |   \---employee
    |       |       |   EmployeeTest.java
    |       |       |
    |       |       +---base
    |       |       |       TestConfig.java
    |       |       |
    |       |       \---utils
    |       |               PropertyUtils.java
    |       |               RestUtils.java
    |       |
    |       \---resources
    |               EmployeeSuite.xml
    |               mock.properties
  
