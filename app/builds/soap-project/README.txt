[DESCRIPTION]
WebService project:
* Contract first: We start with the WSDL/XSD and generate/implement Java code that meets this contract.
This strategy is an alternative to contract last (JAX-WS) where we start with the Java code and then 
auto generate the WSDL. Contract first is the better choice as:
-- Language specific (non portable) types such as TreeMap cannot become exposed in a XSD.
-- We want a stable contract. With contract last, the contract is generate from the code, 
and thus any code changes may result in an altered WSDL.
-- Hand written XML schemas are superior to XML generated from code (which is often poorly structured and unreadable).
-- We want to reuse XML schemas (XSD reuse with contract last is close to impossible).

The project is structured as follows:
* backend: A simple POJO application that we want to expose as a service
* api: Contains contract (holiday.wsdl/hr.xsd) and generates beans that meets this contract. The generated code
is customized via the binding files: wsdl-bindings.xml (customizes WSDL beans) and xsd-bindings.xml (customizes XML beans).
This jar file is build so it can be shared between the client and server (the client can also generate its own beans from 
the WSDL).
* service: Implements the SOAP endpoint via spring-ws. Also contains a request and response example (src/test/resources)   
* ear: Packages service war and sets context root


[GUIDE]
- Import pom.xml from ws-project
- If ws-service fails to compile then do:
## run mvn generate-sources in ws-api project
## reimport ws-api project
## refresh ws-service project
- Deploy ear (or service war at context holidayService).
- Check WSDL at http://localhost:8080/holidayService/hr.wsdl


[LINKS]
* Integration Testing
-- http://onjavahell.blogspot.com/2009/05/integration-testing-with-maven-20.html
-- http://mojo.codehaus.org/failsafe-maven-plugin/
* Spring WS
-- http://static.springsource.org/spring-ws/sites/1.5/reference/html/tutorial.html
-- http://static.springsource.org/spring-ws/sites/1.5/reference/html/server.html
-- http://jamesbnuzzo.wordpress.com/2009/04/14/spring-web-services-with-jaxb-maven-amp-eclipse/
* XSD design
-- http://www.xfront.com/BestPracticesHomepage.html
* WSDL
-- http://www.w3schools.com/WSDL/default.asp
-- http://www.ibm.com/developerworks/webservices/library/ws-tip-jaxrpc.html
-- http://www.ibm.com/developerworks/library/ws-tip-wsdlfaults/index.html
-- http://www.ibm.com/developerworks/webservices/library/ws-jaxws-faults/index.html

[TODO]
- Inject DB connection into backend 
- Use DBunit for instantiating data for integration test
- integration test for war and another for EAR (GFv3).
- rename impl to service
- ensure that site output works
- Blog about this (perhaps multiple posts).
- http://maven.apache.org/plugins/maven-dependency-plugin/usage.html
