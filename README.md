# Project Title
REST API for contact management app

## Getting started
### Prerequisites
Further, you can se a list of prerequisites needed to run this project
* Java 17
* Maven
* PostgreSQL
* JavaEE compatible IDE
* Git
* Postman (optional)

### Instalation
Next, there will be a guide on how to install the needed prerequisites
> Installing Java
* Download the installation file for the Java Development Kit 17 from the next [link](https://www.oracle.com/java/technologies/downloads/#java17 "JDK download").
* Once downloaded, run the installation file and follow the installation wizard. The installation is pretty straightforward
* After that, you need to add the JAVA_HOME user variable and set the path to JDK in the path in system variables. You can follow the next links to help guide you in the process [here](https://www.baeldung.com/java-home-on-windows-7-8-10-mac-os-x-linux "JAVA_HOME") and [here](https://community.akamai.com/customers/s/article/Adding-JDK-Path-in-Mac-OS-X-Linux-or-Windows?language=en_US "JDK to path")
* Finally, you can test if Java is working on your machine

> Installing Maven
* Download the zip file from the next [link](https://maven.apache.org/download.cgi "Maven download")
* Unpack the files from zip to the desired location
* Then you need to set the MAVEN_HOME user variable and set the path to Maven in the path in system variables. You can follow the next link to help guide you in the process [here](https://www.baeldung.com/install-maven-on-windows-linux-mac "MAVEN_HOME")
* Finally, you can test if Maven is working on your machine

> Installing PostgreSQL
* Download the installation file from the next [link](https://www.postgresql.org/download/ "PostgreSQL download")
* Run the installation file and follow the wizard

> Installing Git
* Download the installation file from the next [link](https://git-scm.com/downloads "Git download")
* Run the installation file and follow the wizard

> Installing Postman
* Download the installation file from the next [link](https://www.postman.com/downloads/ "Postman download")
* Run the installation file and follow the wizard

## Executing program
* Clone the repository on your machine using the next command
```bash
git clone https://github.com/TheSpax/contact-management.git
```
* Make sure your PostgreSQL is running
* Open app folder in your IDE and run the program

## Usage
Once the app is up and running you can access the endpoints logged in as admin or user depending on which endpoint you want to access. You can access http://localhost:8080/contact-manager/swagger-ui.html to open the swagger ui documentation for the project.
