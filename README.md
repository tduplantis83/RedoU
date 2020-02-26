# RedoU - A Social Fitness Game

Built by:
* [Travis Duplantis](https://github.com/tduplantis83)


### Overview:

The purpose of this application is to help users get into shape in a fun, social, non-threatening way.

New Users begin by registering on the website. First they enter the following:
* Username
* Password
* First Name
* Last Name
* Email Address (optional)
* Birthday
* Sex

Next, the user will be prompted to choose a primary goal:
* Weight Loss
* Muscle Gain
* General Fitness

Then users will be asked to choose a SET of Avatars based upon the sex they chose during registration.
* Initially the selection of avatars is limited to 3 male and 3 female options, which I created (with each body type - thin, average, fat, athletic, & muscular) using Bitmoji.
* After project completion, I hope to submit the application to Bitmoji, to allow users to create a set of fully personalized avatars.

Finally, users will input their initial body measurements in US or Metric.
* Height
* Weight
* Waist
* Neck (optional)
* Shoulders (optional)
* Chest (optional)
* Bicep (optional)
* Hips (optional)
* Thigh (optional)

Initial body measurements are utilized to choose an initial avatar body type based on BMI, which is calculated from the given measurements.

Users may also utilize the application to track their daily caloric intake as an estimated total.

The user can further achieve their fitness goal by tracking their daily workout caloric burn as an estimated total.




### Database
* Type: MySQL
* Name: RedoU
* Creation / Insert file: redou.sql

### Technologies used:
* MySQL
* MySQL Workbench
* Atom
* Eclipse / Spring Tool Suite
* Java
* Test Driven Development (TDD) utilizing JUNIT 5 Jupiter and Postman
* Spring Framework - Spring Boot & Spring Security
* JPA
* Hibernate
* JPQL
* Object Relational Mapping (ORM) - Entities with relationships matching those in the Database
* Repositories to query the Database
* Service Interfaces and their Implementations, which utilize the application Repositories
* Controller utilizing RESTful api endpoints to communicate with the front end
* Angular 8 utilizing TypeScript
* HTML, CSS, & Bootstrap
