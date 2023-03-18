# Spring/Java: API Basic Role-Based Access Control (RBAC) Code Sample

This Java code sample demonstrates **how to implement Role-Based Access Control (RBAC)** in Spring API servers using Auth0.

This code sample is part of the ["Auth0 Developer Resources"](https://developer.auth0.com/resources), a place where you can explore the authentication and authorization features of the Auth0 Identity Platform.

Visit the ["Spring/Java Code Sample: Role-Based Access Control For Basic APIs"](https://developer.auth0.com/resources/code-samples/api/spring/basic-role-based-access-control) page for instructions on how to configure and run this code sample and how to integrate it with a Single-Page Application (SPA) of your choice.


## Build

To build this project, first create a `.env` file based off `.env.example` and update the vars with the adequate values for your environment.

Make sure you have a Java 17 SDK configured by running `java -version`. 

Then, execute on the project root:

```shell
./gradlew build
```

## Run 

To run the previously generated JAR file, execute on project root:

```shell
java -jar build/libs/helloworld-1.0.0.jar
```

## Why Use Auth0?

Auth0 is a flexible drop-in solution to add authentication and authorization services to your applications. Your team and organization can avoid the cost, time, and risk that come with building your own solution to authenticate and authorize users. We offer tons of guidance and SDKs for you to get started and [integrate Auth0 into your stack easily](https://developer.auth0.com/resources/code-samples/full-stack).
