# WroclArts
WrocłArts is a web-based system designed to manage any artistic activity in Wrocław. It is designed to be an all-in-one platform for every artistic organization or events within Wrocław in order to encourage city residents to participate in these activities or suggest new ones. Project for Laboratory Assignement of "Information System Modelling"

## Versions and code

The version history and source code are available on Github: https://github.com/MaximeLuce/WroclArts .

## Use

1) Load the project
2) Use `mvn clean compile`
3) Launch the project
4) Visit http://localhost:8080/WroclArts/swagger-ui/index.html to explore OpenAPI definition
5) Visit http://localhost:8080/WroclArts/h2-ui/ to explore the database
  - database: db_wroclarts (jdbc:h2:file:./db_wroclarts)
  - username: `sa`
  - password: (none)

## Specific tests for the lab03

The implemented controller can be tested with the followings instructions (see `Documentation/DataStorageStructure_lab03.pdf` for more details):

1) Register a new user with `/users/register` (method: POST)
2) Register a new organization with `/organizations` (method: POST)
3) Create a new event with `/events` (method: POST)
4) Register to an event `/events/{eventId}/tickets` (method: POST)
5) Adding an organization to an existing event `/events/{eventId}/organizations/{orgId}` (method: POST)

## Structure

Main modified files:
- `src/main/java/pl.edu.pwr.tkubik.ism/api` for controllers
- `src/main/java/pl.edu.pwr.tkubik.ism/config` for the webconfig
- `src/main/java/pl.edu.pwr.tkubik.ism/model` for the entities and DTOs
- `src/main/java/pl.edu.pwr.tkubik.ism/repository` for the repository
- `src/main/java/pl.edu.pwr.tkubik.ism/service` for the service
- `src/main/java/pl.edu.pwr.tkubik.ism/` for WroclArtsApplication
- `src/main/ressources/openapi/openapi.yaml` for OpenAPI documentation
- `src/main/ressources/application.properties` for the application properties


## History of the Laboratory

- **Lab01:** Editing the SRS of the project `Documentation/SRS_lab01.pdf`
- **Lab02:** 
  1) REST API documentation fulfilling requirements described in SRS (lab01) compliant with OpenAPI 3 standard.
  2) Updating SpringBoot project
  3) Uploading the API Documentation `Documentation/OpenAPI_Documentation_lab02.pdf`
- **Lab03:**
  1) Adding api, model, repository, service
  2) Updating the YAML
  3) Testing many-to-many using H2-Console (eventRegistration, eventCreation, ...)
  4) Uploading the Documentation `Documentation/DataStorageStructure_lab03.pdf`
- **Lab04:**
  1) add a new controller with getStatistics
  2) AOP 
  
## To-Do
Questions: admins specific endpoint or just in security?

