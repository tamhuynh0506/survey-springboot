Learned Features:
- JPA Repository
- Mapper DTO - Entity
- Add relationship when create entities in entity classes

Learned Designs:
- Use abstract class ApiException and its contructor to:
    + Create custom exceptions: extends ApiException class
    + Then use @ControllerAdvice: a "global handler" for all controllers to catch all the ApiExceptions or all others exceptions
- Dockerized:
    + "The way we setup to run the app in local machine is the way we run on linux machine"
    + Dockerfile is a recipe for building a Docker image. It defines:
        . The base OS image to use (e.g., Ubuntu, Alpine)
        . Dependencies to install
        . Files to copy into the image
        . Commands to run when building or starting the container
    + Mounting in Docker means connecting a folder or file from host machine to a container so that the container can read or write to it
