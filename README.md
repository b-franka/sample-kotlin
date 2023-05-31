## Spring Boot application with Gradle and Kotlin

### Requires JDK 17

### run the application on localhost:8080
#### with gradle from CLI
`./gradlew bootRun`

#### build Docker image from Dockerfile
- in root dir
- `docker build .`
- `docker run -p 8080:8080 <image name>`

### healthcheck
localhost:8080/actuator/health

### swagger-UI available
localhost:8080/swagger-ui.html

### exposed API endpoints
- GET api/record
- GET api/invoice/{docName}
- POST api/invoice

### Openapi Design first
- generated API interfaces and model classes in build/generated-oas
- api spec: resources/api-spec.yaml
