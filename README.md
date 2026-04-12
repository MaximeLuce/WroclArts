# WroclArts
WrocłArts is a web-based system designed to manage any artistic activity in Wrocław. It is designed to be an all-in-one platform for every artistic organization or events within Wrocław in order to encourage city residents to participate in these activities or suggest new ones. Project for Laboratory Assignement of "Information System Modelling"

## Use

1) Load the project
2) Use `mvn clean compile`
3) Launch the project
4) Visit http://localhost:8080/WroclArts/swagger-ui/index.html to explore OpenAPI definition
5) Visit http://localhost:8080/WroclArts/h2-ui to explore the database

## Structure

Main modified files:
- `src/main/java/pl.edu.pwr.tkubik.ism/api` for controllers
- `src/main/java/pl.edu.pwr.tkubik.ism/api` for WroclArtsApplication
- `src/main/ressources/openapi/openapi.yaml` for OpenAPI documentation

## To-Do
Questions: admins specific endpoint or just in security?

## History of the Laboratory

- **Lab01:** Editing the SRS of the project `Documentation/SRS_lab01.pdf`
- **Lab02:** 
  1) REST API documentation fulfilling requirements described in SRS (lab01) compliant with OpenAPI 3 standard.
  2) Updating SpringBoot project
  3) Uploading the API Documentation `Documentation/OpenAPI_Documentation_lab02.pdf`
- **Lab03:**
  1) Adding api, model, repository, service
  2) Updating the YAML
  3) Testing many-to-many using H2-Console (eventRegistration, eventCreation)
  4) Uploading the Documentation `Documentation/X.pdf` (TO-DO)

