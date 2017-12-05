Build pipeline:  

 1. "swagger-codegen-maven-plugin" generates model and API code for
    Spring MVC server 
 2. Maven builds and installs generated server code
    and installs to local repository as artifact
 3. Spring Boot application includes dependency of generated server
    artifact, and has additional configuration, properties and real
    implementation of controllers
 4. "swagger-codegen-maven-plugin" generates model and API code for
    Angular Client. The output directory is under "mem-client" angular
    project.
 5. "exec-maven-plugin" installs client dependencies (npm install)
    "exec-maven-plugin" builds application (ng build --env=prod …) the
    output of build stored in Spring Boot application resources
 6. Once Angular client compiled and added to static server resources, we could
    create standalone JAR with spring server and client
    ("spring-boot-maven-plugin" can do it)

NOTE: Please note that generated server and client codes are read-only and included only as dependencies/imports. Real implementation of controllers in separate project. To start server/client coding, execute "mvn generate-sources". To create server+client as standalone web application, just execute "mvn install" in parent "mem" project. This will create jar with all necessary stuff included in it.

Projects overview:

 1. Parent maven pom "mem" project which includes all child modules
 2. Maven "mem-design" project includes only Swagger 2.0 yaml file in
    resources.
 3. Maven "mem-server" project includes Spring Boot configuration,
    properties and real controllers
 4. Angular 5 client project "mem-client"
 5. Auto generated code (not touched, just used in read-only mode as
    import. Ignored in source control) 
