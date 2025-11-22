Learned Features:
- JPA Repository
- Mapper DTO - Entity using MapStruct
- Add relationship when create entities in entity classes (e.g: @ManyToOne)
- DTO fields validation raises MethodArgumentNotValidException
- Only class that represent a bean can be injected:
    + @Service
    + @Component
    + @Repository (no need if class already extended JpaRepository)
    + @Mapper(componentModel = "spring")
    + @RestController
    + ...

Learned Designs:
- Use abstract class ApiException and its constructor to:
    + Create custom exceptions: extends ApiException class
    + Then use @ControllerAdvice: a "global handler" for all controllers to catch all the ApiExceptions or all others exceptions
- Dockerized:
    + "The way we set up to run the app in local machine is the way we run on linux machine"
    + Dockerfile is a recipe for building a Docker image. It defines:
        * The base OS image to use (e.g., Ubuntu, Alpine)
        * Dependencies to install
        * Files to copy into the image
        * Commands to run when building or starting the container
    + Mounting in Docker means connecting a folder or file from host machine to a container so that the container can read or write to it
- Email verify flow (the simplest way):
    + setVerificationToken and its expired date when register
    + In verify controller, query user by that token then check its expired date
    + If user exists, set email verify flag to True then set that token to null
- Forgot and reset password flow (the simplest way):
    + setResetPasswordToken and its expired date when forgot password then return reset password link
    + In reset password controller, query user by that token then check its expired date
    + If user exists, setPassword to new password that get from request body
