# JPA Demo Project

## TODO
- Extended persistence context
- Use spring transaction and configure spring using Java
 - http://blog.jhades.org/how-does-spring-transactional-really-work/
 - http://stackoverflow.com/questions/1099025/spring-transactional-what-happens-in-background
- Event listeners 
  * AbstractEventListener (AbstractReassociateEventListener)
  * @PrePersist and @PostPersist
  * @PreUpdate and @PostUpdate
  * @PreRemove and @PostRemove
  * @PostLoad
  * @EntityListeners
- investigate new JPA annotations not in use in example
- add stored procedures http://stackoverflow.com/questions/10338044/create-a-stored-procedure-in-hsqldb-with-spring-embedded-databases-api
- add save points http://alvinalexander.com/java/jwarehouse/hsqldb/src/org/hsqldb/test/TestJDBCSavepoints.java.shtml
    - HSQLDB save points http://hsqldb.org/doc/guide/management-chapt.html
- add triggers
- add example of fetch join (select p FROM Person p left join fetch p.invoices)