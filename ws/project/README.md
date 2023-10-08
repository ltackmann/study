## TODO
 * Get report publishing working

[DESCRIPTION]
WebService project with the following properties

* Contract first: We start with the WSDL/XSD and generate/implement Java code that meets this contract.
This strategy is an alternative to contract last (JAX-WS) where we start with the Java code and then
auto generate the WSDL. Contract first is the better choice as:
-- Language specific (non portable) types such as TreeMap cannot become exposed in a XSD.
-- We want a stable contract. With contract last, the contract is generate from the code,
and thus any code changes may result in an altered WSDL.
-- Hand written XML schemas are superior to XML generated from code (which is often poorly structured and unreadable).
-- We want to reuse XML schemas (XSD reuse with contract last is close to impossible).

The project is structured as follows:
- ws-backend: A simple POJO application that we want to expose as a service
- ws-api: Contains contract (holiday.wsdl/hr.xsd) and generates beans that meets this contract. The generated code
is customized via the binding files: wsdl-bindings.xml (customizes WSDL beans) and xsd-bindings.xml (customizes XML beans).
This jar file is build so it can be shared between the client and server (the client can also generate its own beans from
the WSDL).
- ws-impl: Implements the SOAP endpoint via spring-ws. Also contains a request and response example (src/test/resources)


[GUIDE]
- Import pom.xml from ws-project


[LINKS]
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
