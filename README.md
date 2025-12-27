Learned Features:
- JPA Repository:
    + @Query: to create complex queries and can select into a DTO
    + Can return Page objects to work with pagination

- Mapper DTO - Entity using MapStruct (lombok must before map struct in dependencies order)

- Add relationship when create entities in entity classes (e.g: @ManyToOne)

- DTO fields validation raises MethodArgumentNotValidException

- Only class that represent a bean can be injected:
    + @Service
    + @Component
    + @Repository (no need if class already extended JpaRepository)
    + @Mapper(componentModel = "spring")
    + @RestController
    + ...
  
- PUT: full update | PATCH: partial update

- Can get info from SecurityContextHolder.getContext().getAuthentication()

```
  public       static                    <T>        ResponseEntity<ApiResponse<T>>    success(...)
               ──┬──                    ──┬──       ────────────────┬─────────────
          call this method        declares T exists        return type uses T
          without creating 
          an object of the class.          
```

- TODO: about mapper, need to dive in more and more to make notes

- Add @EnableMethodSecurity(prePostEnabled = true) to use @PreAuthorize

- Add @AllArgsConstructor (All fields) or @RequiredArgsConstructor (final + @NonNull fields only) to auto create constructors

- Use record or class in DTO:
| Use `record` when…              | Use `class` when…        |
| ------------------------------- | ------------------------ |
| DTO is immutable                | Object must be mutable   |
| Pure data carrier               | Has behavior / logic     |
| Request / response models       | Entities / domain models |
| Equals/hashCode/toString matter | Custom control needed    |
| API contracts                   | Business objects         |

- Use @Transactional for methods that:
    + Perform multiple database actions. E.g:
        * The first operator will be rollback when the second raises error
        * Save multiple or all records at the same time
    + Must rollback if an error occurs
    + Are read-only but want optimized performance.

- OncePerRequestFilter:
    + Ensures logic only executes once, preventing bugs like:
        * Double authentication 
        * Double logging 
        * Extra DB calls 
       
    + Used for custom security filters. For example:
        * JWT authentication
        * Logging request metadata 
        * IP checking
        * Rate limiting (simple per-request checks)

- Use Flyway to do the data migration
    + Version order, e.g: V1__ -> V2__, V<date_before> -> V<date_after>

- Spring Security has UserDetailsService that need to know how we are getting user then use that user in doFilterInternal method
- Combined Spring Security lifecycle diagram:
``` 
    Client HTTP Request
              │
              ▼
┌───────────────────────────────┐
│  Servlet Container (Tomcat)   │
│  Receives HTTP request        │
└───────────────────────────────┘
              │
              ▼
┌───────────────────────────────┐
│  springSecurityFilterChain    │  ← Your SecurityFilterChain bean
│  (called automatically)       │
└───────────────────────────────┘
              │
              ▼
┌──────────────────────────────────────────┐
│  JwtAuthFilter (OncePerRequestFilter)    │
│  - Extract JWT from Authorization header │
│  - Validate token                        │
│  - Load user details                     │
│  - Set Authentication in SecurityContext │
└──────────────────────────────────────────┘
              │
              ▼
┌─────────────────────────────────────────┐
│  Other Security Filters                 │
│  - UsernamePasswordAuthenticationFilter │
│  - FilterSecurityInterceptor            │
│  - CsrfFilter / CorsFilter              │
└─────────────────────────────────────────┘
              │
              ▼
┌──────────────────────────────────────────────┐
│  Authentication present?                     │
│  ┌─────────────┐                             │
│  │ YES         │ → Continue to authorization │
│  └─────────────┘                             │
│  ┌─────────────┐                             │
│  │ NO          │ → Trigger                   │
│  │             │   AuthenticationEntryPoint  │
│  │             │   (401 Unauthorized)        │
│  └─────────────┘                             │
└──────────────────────────────────────────────┘
              │
              ▼
┌──────────────────────────────────────────────────┐
│  Authorization check (FilterSecurityInterceptor) │
│  Has required role/authority?                    │
│  ┌─────────────┐                                 │
│  │ YES         │ → Continue to Controller        │
│  └─────────────┘                                 │
│  ┌─────────────┐                                 │
│  │ NO          │ → Trigger                       │
│  │             │   AccessDeniedHandler           │
│  │             │   (403 Forbidden)               │
│  └─────────────┘                                 │
└──────────────────────────────────────────────────┘
              │
              ▼
┌───────────────────────────────┐
│  Controller / REST endpoint   │  ← Only called if authenticated & authorized
└───────────────────────────────┘
              │
              ▼
       Client HTTP Response

```
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

- Create Admin in migration file
