SMAL2
====

J2EE project to manage support requisitions of computers' lab maintenance at CEFET-RJ University.





### Test and Run

<pre>
mvn clean compile test * to run test
mvn tomcat:run # to run web server on http://localhost:8080/smal/
</pre>



### To use Eclipse project

Run this before open eclipse project:

<pre>
mvn compile install
</pre>

Then, open eclipse project, click with right botton on project and do a refresh to get maven auto-updated dependencies.



### HSQLDB (embedded db) and the sql editor SQL Workbench:

Open SQL Workbench,
copy HSQLDB jar (auto-downloaded by maven into 'target' directory) in SQL Workbench directory
reference and use it, for example

<pre>
Driver      HSQLDB
URL         jdbc:hsqldb:/tmp/db/smal2
Username    sa
Password
</pre>



### References
<pre>
 http://www.campisano.org/wiki/en/Maven_manual_eclipse_project
 http://search.maven.org/#search
 http://www.ibm.com/developerworks/library/wa-datawebapp/
 http://blog.wolfgang-werner.net/restful-http-with-jax-rs/
 http://www.javacodegeeks.com/2012/05/tutorial-hibernate-jpa-part-1.html
 http://www.mkyong.com/webservices/jax-rs/json-example-with-jersey-jackson/
 http://www.mkyong.com/webservices/jax-rs/jersey-spring-integration-example/
 http://learn.knockoutjs.com/#/?tutorial=loadingsaving
 http://www.sql-workbench.net/
 http://hsqldb.org/
</pre>
