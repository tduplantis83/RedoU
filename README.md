# RedoU - A Social Fitness Game

Built by:
* [Travis Duplantis](https://github.com/tduplantis83)


### Overview:
The purpose of this application is to help users get into shape in a fun, social, non-threatening way.


#### Registration:
1. New Users begin by registering on the website. First they enter the following:
 * Username
 * Password
 * First Name
 * Last Name
 * Email Address (optional)
 * Birthday
 * Sex

After this point, the user is technically registered and logged in; however, they lack any other information and it is therefore suggested that to complete registration, they must complete steps 2-4. If the User does NOT complete these final steps, they may choose to Cancel Registration, which will delete them, log them out, and redirect them to the application home page.

2. The user will be prompted to choose a primary goal:
 * Weight Loss
 * Muscle Gain
 * General Fitness

3. Users will be asked to choose a SET of Avatars based upon the sex they chose during registration.
 * Initially the selection of avatars is limited to 3 male and 3 female options, which I created (with each body type - thin, average, fat, athletic, & muscular) using Bitmoji.
 * FUTURE IMPLEMENTATION - After project completion, I hope to submit the application to Bitmoji, to allow users to create a set of fully personalized avatars.

4. Finally, users will input their initial body measurements in US or Metric.
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


#### Usage:

##### User Profile:
1. Users may utilize the application to Create, Update, or Delete their daily caloric intake per meal (breakfast, lunch, dinner, snack, dessert) as an estimated total (multiple entries per day).

2. The user can further achieve their fitness goal by Creating, Updating, or Deleting their daily caloric burn as an estimated total (one entry per day).

3. A user's caloric intake and deficit are utilized to provide feedback showing how many calories the user gained or lost for that day; this is used to show whether the user's daily activity resulted in Weight Loss or Weight Gain.

4. Further feedback is provided to motivate the user by showing two versions of their chosen avatar:
  * A BMI-Based Avatar (based on most recently provided body measurements)
  * A Behavior-Based Avatar (based on the user's most recently entered daily caloric performance):
   * EX 1: User BMI-Based avatar body type is Average. If the user consumes more calories than they burn for the day, their Behavior-Based Avatar body type will change to one with a Fat body type.
   * EX 2: User BMI-Based avatar body type is Average. If the user burns more calories than they eat for the day, their Behavior-Based Avatar body type will change to one with a Thin body type.

5. Users may choose to provide actual body image(s) (one front & one side-facing) per day, in order to watch physical changes as they occur in their body over time. These images are ONLY shown to the logged in user, on their personal profile page. Of course the user has the ability to Update and Delete these images as well.

6. The user can view any unread replies to a Forum Post they may have made, and mark the reply as read.


##### Forum:
1. Any user may read through the website's Forum Posts and replies

2. A registered and logged-in user can also Create new Forum Posts. If the post belongs to the logged in user, they may also Update and Delete said post.


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
