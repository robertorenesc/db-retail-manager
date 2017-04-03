# Retail Manager Demo
Copyright (c) 2017 GFT Group

---
## Setup
1. Install [Java Development Kit 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
2. Check if `JAVA_HOME` variable is set:
    - Mac OS/Linux:
        - `echo $JAVA_HOME`
    - Windows:
        - `echo %JAVA_HOME%`
3. If `JAVA_HOME` variable is NOT set:
    - Mac OS/Linux:
        - `vi ~/.bash_profile`
        - Add `export JAVA_HOME=<PATH_TO_JDK>`, where `<PATH_TO_JDK>` - path to JDK location (e.g. `/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home`)
    - Windows:
        - Right click My Computer and select Properties.
        - On the Advanced tab, select Environment Variables, and then edit JAVA_HOME to point to where the JDK software is located (e.g. `C:\Program Files\Java\jdk1.8.0_45`).

## Getting started
- To run the server, from the root of the project directory:
    - Mac OS/Linux:
        - `./gradlew bootRun`
    - Windows:
        - `gradlew.bat bootRun`

## Gradle tasks
##Run tasks
- `bootRun` - Run the project with support for auto-detecting main class and reloading static resources

##Clean tasks
- `clean` - Delete the build directory

##Clean tasks
- `test` - Run unit testing of the project

## How to Use
The present demo has all necesary information and tools to be built and executed without other tools than Java 8, once the server is started, next some deatils about the implementation:

- Run the project using the Run Task command specified in Gradle Tasks section

- In memory database H2 is used to store the persistent data, that data is reset every time the server is started, to browse the database you have to go to the link http://localhost:8082 and use the next parameters:
    - `JDBC URL` - jdbc:h2:mem:db_test
    - `User name` - sa
    
- Import in Postman the rest sample file Retail Manager.postman_collection located in the root of the project