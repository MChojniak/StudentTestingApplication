# StudentTestingApplication

Hello.

This application was meant for my college classes group project designing.
We had to implement application that allows professors to test their students.

That was my first project I have fully accomplished to it's working state, with pretty much no experience in Java Spring.
My team chose to switch to it 2 months before deadline,
so as a main-and-only dev in my team I made this application in about 3 - 4 weeks.
Please keep it in mind while you are looking at the code.

>Project was done with 06/14/2019.

### Technologies

StudentTestingApplication is working using open source projects to work:

* [Spring]
    * [Spring Boot]
    * [Spring Security]
    * [JPA]
        * [Hibernate]
    * [MockMVC]
* [Database-H2] - in memory Java SQL database
* [Maven]
* [Thymeleaf]
* [Bootstrap]

##### Installation guide
###### Working application is accessible on my google drive: [DOWNLOAD](https://drive.google.com/open?id=1mjt7gJGes33e_6cn2d6PMOpMyi7dFMQx).
```sh
$ Install JDK 1.8 or higher.
$ Download and unzip library.
$ Run CMD.
$ Run command as standard executable jar: java - jar $PATH\${APPLICATION.JAR}
$ Run WebBrowser and go to: localhost:8080/
$ Database is in memory, you need to login in order to check database (Spring Security)
$ Credentials:
     Admin account (professor):
     Login: adminTest@test.pl
     Password: AdminTest!1
     BOTH LOGIN AND PASSWORD ARE CASE-SENSITIVE !!
# You can create other accounts.
# Database:
    # accessible with: localhost:8080/h2-console (case sensitive)
    # no credentials needed (beside previously log in to application).
```
`$PATH` - path to folder with unzipped package

`${APPLICATION.JAR}` - in this case: testy-sb-0.0.1-SNAPSHOT.jar

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

[Spring]:<https://spring.io/>
[Spring Boot]:<https://spring.io/projects/spring-boot>
[Spring Security]:<https://spring.io/projects/spring-security>
[MockMVC]:<https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/test/web/servlet/MockMvc.html>
[Database-H2]:<https://www.h2database.com/html/main.html>
[JPA]:<https://spring.io/projects/spring-data-jpa>
[Hibernate]:<https://hibernate.org/>
[Maven]:<https://maven.apache.org/>
[Thymeleaf]:<https://www.thymeleaf.org/>
[Bootstrap]:<https://getbootstrap.com/>
