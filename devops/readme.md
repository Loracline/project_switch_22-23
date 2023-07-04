# Project Management System - DevOps #

## Main Goal 

To implement a Jenkins pipeline for our Project Management System project, incorporating key
DevOps principles.

## Introduction

Applying DevOps fundamental concepts to our Project Management System project is crucial for its success, as it will 
have a profound impact on our development and delivery processes. By leveraging Infrastructure as Code, Git for 
collaboration, and utilizing multiple hosts in Jenkins and Docker, we can achieve greater scalability, reliability, and 
flexibility in our project. The Jenkins pipeline automates crucial stages such as application assembly, testing, and 
artifact publishing, resulting in faster feedback loops and shorter release cycles. This automation enhances our ability 
to deliver high-quality software consistently. Additionally, integrating Docker ensures portability and consistency 
across different environments, further facilitating efficient and reliable deployments. Ultimately, implementing a 
Jenkins pipeline in our Project Management System project enables us to optimize our development workflow, enhance 
collaboration, and streamline the delivery process, ultimately resulting in a more efficient and successful project.


### General Requirements

#### **Infrastructure as Code**
- Our solution must be reproduced in any system by cloning our repository.
- Images uploaded to Docker Hub must be used to facilitate easy setup and deployment.

#### **Git / Bitbucket**
- As it has been done so far, tasks and issues must be created to track the work of all team members.

### Jenkins Requirements

For the Jenkins solution, two hosts must be set up in a "docker-compose.yml" file: 
- **Control host** This host must have Jenkins installed, acting as the central control point for our pipeline.
- **Dind host** This host should have Docker-in-Docker installed and will be used by Jenkins for container operations. 

The Jenkins pipeline must have the following stages:
- **Assemble** This stage involves compiling and building our application, ensuring that it is ready for testing and 
deployment.
- **Generate and Publish Javadoc** In this stage, Javadoc documentation must be generated for our code and published 
within Jenkins. This documentation helps in understanding the codebase and its usage. 
- **Execute Tests (Unit / Integration) and Publish Results** This stage focuses on running both unit and integration 
tests for our application. The test results, including any code coverage metrics, should be published in Jenkins. This 
helps assess the quality and reliability of our code. 
- **Publish Distributable Artifacts** In this stage, distributable artifacts such as .war files, must be published. 
These artifacts are the final outputs of our build and can be used for deployment. 
- **Publish Image** Our pipeline must integrate with Docker, which means that an image of our application must be built 
and published in Docker Hub. The Docker image must be appropriately tagged to reflect the build version, ensuring 
version control. Additionally, it must also be tagged as "latest" to facilitate easy access to the most recent version
of the image. 

### Docker Requirements

Regarding the deployment solution, three hosts must be defined and managed in another "docker-compose.yml" file:
#### **Web host** 
This host must be responsible for presenting the Project Management System web application to users. The 
App container is deployed on the Web host.

**App container** is deployed on the Web host:
- Uses a Tomcat server
- Connects to the Database container to persist the information
- Uses the image published in Docker Hub from Jenkins (see Jenkins Requirements)

#### **DB host** 
This host will store the application's database, using MariaDB or MySQL. The database should be stored in a host volume 
for data persistence.

**Database container** is deployed on the DB host:
- Uses MariaDB or MySQL
- Database information stored in a host volume, ensuring that data persists even if the container is stopped or removed

#### **PHPMyAdmin host** 
This host must enable view and manage the project database. It connects to the DB host for accessing the database. 

**PhpMyAdmin container** is deployed on the PHPMyAdmin host:
- Separate service in the "docker-compose.yml" file
- Connects to the Database container and provides an interface for database management

System parameters must be stored in a unique ".env" file. 

## Step-by-step

### Deployment solution

#### Step 1

When it comes to deploying a front-end and a back-end application using Docker, you have a couple of options,
you can either deploy them together in the same container or separate them into individual containers.
We have decided to deploy them separately for scalability, flexibility and security reasons.

This approach has some benefits:
- It reduces the chances of attackers gaining access to sensitive information or manipulating the system. By keeping the frontend and backend separate, we limit the points of entry for potential attacks.
- It allows us to control access to sensitive data more precisely. We can set up stricter rules for accessing the backend, making it harder for unauthorized users or systems to interact with it.
- It helps with managing the performance and scalability of the application. We can scale the frontend and backend independently based on their specific needs. This way, we can optimize resources and ensure that the application runs smoothly even under high demand.
- It makes it easier to maintain and update the application. By separating the frontend and backend, developers can work on their respective parts without interfering with each other's work. This separation allows for faster development, testing, and debugging.
- It provides the flexibility to choose different technologies for the frontend and backend. This means we can select the best tools and frameworks for each part of the application, making development more efficient and effective.

This means we have two different Dockerfiles, as you can see below:

**Dockerfile-frontend**

```bash
FROM node:18.15.0 as frontend-build

WORKDIR /app

RUN git clone https://ruipppinho@bitbucket.org/joaoserra1993/project_g4_open_doors.git

WORKDIR /app/project_g4_open_doors/ui

RUN npm ci

RUN npm run build

EXPOSE 3000

CMD [ "npx", "serve", "build" ]
```

- `FROM node:18.15.0 as frontend-build`: This sets the base image for the Docker image as node:18.15.0 with the name `frontend-build`.
- `WORKDIR /app`: This instruction sets the working directory inside the container to /app.
- `RUN git clone https://ruipppinho@bitbucket.org/joaoserra1993/project_g4_open_doors.git`: This command clones a Git repository from the given URL into the /app directory within the container.
- `WORKDIR /app/project_g4_open_doors/ui`: This changes the working directory to /app/project_g4_open_doors/ui within the container.
- `RUN npm ci`: This command installs the project dependencies using npm (Node Package Manager) in the current working directory.
- `RUN npm run build`: This runs the build script defined in the project's package.json file. This script is responsible for building the project, likely compiling code, bundling assets, and preparing the application for production.
- `EXPOSE 3000`: This instruction informs Docker that the container will listen on port 3000.
- `CMD [ "npx", "serve", "build" ]`: This sets the default command to run when the container starts. It executes the serve command using npx (Node Package Runner) and specifies the build directory as the static content to serve.

When you use BrowserRouter in a React app, the routing is handled on the client-side using JavaScript.
By default, a web server would try to find a file that matches the requested URL. However, in a React app, all routes are handled by the client-side router,
and there are no physical files for each route. Therefore, you need to configure the server to serve( create  `serve.json` file ) the index.html file for all routes and
let the client-side router handle the routing internally.
You can now update the `serve.json` file with the following configuration:

```bash
{
"rewrites": [
{"source": "**",
"destination":  "/index.html"}
]
}
```
This configuration instructs the server to rewrite all requests (any) to the index.html file.

See more on : <sup>[1](https://create-react-app.dev/docs/deployment/),</sup>
<sup>[2](https://www.npmjs.com/package/serve),</sup>
<sup>[3](https://github.com/vercel/serve-handler#redirects-array)</sup>


**Dockerfile-backend**

```bash
FROM maven:3.9.1 as backend-build

WORKDIR /app

RUN git clone https://ruipppinho@bitbucket.org/joaoserra1993/project_g4_open_doors.git

WORKDIR /app/project_g4_open_doors

RUN mvn dependency:go-offline

RUN mvn package -DskipTests

FROM tomcat:9.0.54-jdk11

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=backend-build /app/project_g4_open_doors/target/switch2022project-g4-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
```

- `FROM maven:3.9.1 as backend-build`: This sets the base image for this stage as maven:3.9.1 with the name `backend-build`.
- `WORKDIR /app`: This sets the working directory inside the container to /app.
- `RUN git clone https://ruipppinho@bitbucket.org/joaoserra1993/project_g4_open_doors.git`: This command clones a Git repository from the given URL into the /app directory within the container.
- `WORKDIR /app/project_g4_open_doors`: This changes the working directory to /app/project_g4_open_doors within the container.
- `RUN mvn dependency:go-offline`: This command uses Maven to download and cache all the project dependencies specified in the pom.xml file.
- `RUN mvn package -DskipTests`: This command runs the package goal of Maven, which compiles the Java code, runs tests (skipped in this case with -DskipTests flag), and packages the application into a WAR (Web Application Archive) file.
- `FROM tomcat:9.0.54-jdk11`: This sets the base image for the final container as tomcat:9.0.54-jdk11, which includes Apache Tomcat, a Java-based web server and servlet container.
- `RUN rm -rf /usr/local/tomcat/webapps/*`: This command removes the default web applications that come with the Tomcat image. It clears the webapps directory to prepare for deploying the custom-built application.
- `COPY --from=backend-build /app/project_g4_open_doors/target/switch2022project-g4-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war`: This instruction copies the WAR file generated in the previous stage (backend-build) from the specified location (/app/project_g4_open_doors/target/switch2022project-g4-1.0-SNAPSHOT.war) to the webapps directory of the Tomcat container. The destination file is named ROOT.war, which means it will be deployed as the default application at the root context.
- `EXPOSE 8080`: This instruction informs Docker that the container will listen on port 8080.
- `CMD ["catalina.sh", "run"]`: This sets the default command to run when the container starts. It uses the catalina.sh script provided by Tomcat to start the Tomcat server and run the deployed application.

Note that this Dockerfile requires the following alterations to pom.xml :
```bash
        <packaging>war</packaging>
```
```bash
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>
```

#### Step 2
Create a pipeline scrip with the following stages:
- Checkout: To check out the code from the repository.
- Assemble: Compiles and produces the war archive files with the application.
- Test: Executes the unit tests and publish in Jenkins the test results.
- Javadoc: Generates the Javadoc of the project and publish it in Jenkins.
- Archive: Archives in Jenkins the archive files generated during "Assemble" stage.
- Publish Image: Generate a docker image with Tomcat and the war file and publish it in the Docker Hub.

Add a new `Jenkinsfile` in the DevOps folder:
```bash
pipeline {
    agent any


    stages {
        stage('Checkout') {
            agent {
                docker {
                    image 'maven:3.9.0-eclipse-temurin-11'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                echo 'Checking out...'
                git branch: 'master', url: 'https://bitbucket.org/joaoserra1993/project_g4_open_doors'
            }
        }
        stage('Assemble') {
            agent {
                docker {
                    image 'maven:3.9.0-eclipse-temurin-11'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                echo 'Assembling...'
                script {
                    if (isUnix()) {
                        sh 'mvn clean package'
                    } else {
                        bat 'mvn clean package'
                    }
                }
            }
        }
        stage('Integration Test') {
            agent {
                docker {
                    image 'maven:3.9.0-eclipse-temurin-11'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                echo 'Running integration tests...'
                sh 'mvn failsafe:integration-test'
            }
        }
        stage('Unit Test') {
            agent {
                docker {
                    image 'maven:3.9.0-eclipse-temurin-11'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                echo 'Running unit tests...'
                sh 'mvn test jacoco:report'
            }
        }
        stage('Publish Test Results and Coverage') {
            steps {
                echo 'Publishing test results and coverage...'
                junit '**/target/surefire-reports/*.xml'
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'target/site/jacoco',
                    reportFiles: 'index.html',
                    reportName: 'Code Coverage'
                ])
            }
        }
        stage('Publish Integration Test') {
            steps {
                echo 'Publishing test results and coverage...'
                junit '**/target/failsafe-reports/*.xml'
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'target/site',
                    reportFiles: 'index.html',
                    reportName: 'Integration Test'
                ])
            }
        }
        stage('Generate Javadoc') {
            agent {
                docker {
                    image 'maven:3.9.0-eclipse-temurin-11'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                echo 'Generating Javadoc...'
                script {
                    if (isUnix()) {
                        sh 'mvn javadoc:javadoc'
                    } else {
                        bat 'mvn javadoc:javadoc'
                    }
                }
            }
        }
        stage('Publish Javadoc') {
            steps {
                echo 'Publishing Javadoc...'
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'target/site/apidocs',
                    reportFiles: 'index.html',
                    reportName: 'Javadoc'
                ])
            }
        }
        stage('Archive') {
            steps {
                echo 'Archiving...'
                archiveArtifacts 'target/*.war'
            }
        }
        stage('Publish Image') {
            steps {
            echo 'Building and publishing Docker image...'
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerHubCredentials') {
                        def frontendImage =  docker.build("ruipppinho/g4:frontend${env.BUILD_ID}","-f ./Dockerfile-frontend ." )
                        def backendImage  =  docker.build("ruipppinho/g4:backend${env.BUILD_ID}","-f ./Dockerfile-backend ." )
                        frontendImage.push()
                        backendImage.push()
                    }
                }
            }
        }
    }
}
```
#### Understanding the Jenkinsfile:
**Agent Section**: The agent section specifies where that the pipeline will run in a Docker agent
with the Maven image maven:3.9.0-eclipse-temurin-11. This step is necessary so that Jenkins can run
a maven project.

**Stages Section**: This configuration ensures that the stage runs inside a Docker container with Maven installed,
allowing Maven-based build operations to be performed within the Jenkins pipeline.

**Checkout Stage**: Checks out the source code from a Git repository hosted on Bitbucket.
It uses the git command with the specified repository URL, branch (master), and credentials
(project_g4).

**Assemble Stage**: Here the project is compiled and packaged  using Maven with the command
`mvn clean package`.

**Integration Test Stage**: Runs integration tests using Maven's failsafe plugin with the command
`mvn failsafe:integration-test`.

**Unit Test Stage**: Runs unit tests using Maven's test goal and generates a code coverage report
with jacoco plugin by using the command  `mvn test jacoco:report`.

**Publish Test Results and Coverage Stage**: Publishes the test results and code coverage report.
It uses the junit step to process the test result XML files:
`**/target/surefire-reports/*.xml`. The publishHTML step publishes the HTML-based code coverage
report located in the target/site/jacoco directory.

**Publish Integration Test Stage**: Publishes the integration test results. It uses
the junit step to process the XML files (**/target/failsafe-reports/*.xml) containing the
integration test results. The publishHTML step publishes the HTML-based integration test report
located in the target/site directory.

**Generate Javadoc Stage**: Generates Javadoc documentation for the project with the command
`mvn javadoc:javadoc`.

**Publish Javadoc Stage**: Publishes the generated Javadoc documentation. The publishHTML step
publishes the HTML-based Javadoc report located in the target/site/apidocs
directory.

**Archive Stage**: Archives the generated artifact (.war file) located in the target
directory using the archiveArtifacts step.

**Publish Image**: this stage builds and publishes Docker images for the frontend and backend components of the project,
using the provided Dockerfiles and pushing them to a Docker registry for further use or distribution.


#### Step 3
We will use Docker in Docker to configure Jenkins to run the pipeline.

1. Install Jenkins that can run Docker containers
   Follow the next steps so that Jenkins, which is running in a Docker container, can run other
   containers (this steps follow the tutorial available in https://www.jenkins.io/doc/book/installing/docker/):

```bash
version: '3'

services:
  jenkins-docker:
    image: docker:dind
    container_name: jenkins-docker
    privileged: true
    networks:
      jenkins:
        aliases:
          - docker
    environment:
      - DOCKER_TLS_CERTDIR=/certs
    volumes:
      - jenkins-docker-certs:/certs/client
      - jenkins-data:/var/jenkins_home
    ports:
      - '2376:2376'

  jenkins-blueocean:
    build:
      context: .
      dockerfile: Dockerfile
    image: myjenkins-blueocean:2.401.1-1
    container_name: jenkins-blueocean
    restart: on-failure
    networks:
      - jenkins
    environment:
      - DOCKER_HOST=tcp://docker:2376
      - DOCKER_CERT_PATH=/certs/client
      - DOCKER_TLS_VERIFY=1
    ports:
      - '8080:8080'
      - '50000:50000'
    volumes:
      - jenkins-data:/var/jenkins_home
      - jenkins-docker-certs:/certs/client:ro

networks:
  jenkins:
```
If you have previously run jenkins in a container,you can use the configuration data stored in the created volume. In that case, add the following:
```bash
volumes:
  jenkins-data:
    external: true
  jenkins-docker-certs:
    external: true
```
If you are setting up jenkins through a container for the first time, change the volume section with the following:

```bash
volumes:
  jenkins-data:
  jenkins-docker-certs:
```
You are now able to continue the setup in your browser.


This block defines the jenkins-docker service. Here's a breakdown of each line:
- `version: '3'`: This line specifies the version of the Docker Compose file format being used.
- `services`: This section defines the services that will be created and run using Docker.
- `image: docker:dind`: Specifies the Docker image to use for this service, in this case, `docker:dind`, which provides Docker inside Docker functionality.
- `container_name: jenkins-docker`: Sets the name of the container to `jenkins-docker`.
- `privileged: true`: Grants extended privileges to the container, allowing it to perform privileged operations.
- `networks`: Specifies the networks to which this service will be connected.
- `jenkins`: Refers to the jenkins network defined later in the file.
- `aliases` Specifies an alias for the service within the network. In this case, docker is used.
- `environment`: Defines environment variables for the container.
- `- DOCKER_TLS_CERTDIR=/certs`: Sets the DOCKER_TLS_CERTDIR environment variable to /certs.
- `volumes`: Mounts volumes to the container.
- `- jenkins-docker-certs:/certs/client`: Mounts the jenkins-docker-certs volume to the /certs/client directory in the container.
- `- jenkins-data:/var/jenkins_home`: Mounts the jenkins-data volume to the /var/jenkins_home directory in the container.
- `ports`: Exposes ports from the container to the host.
- `- '2376:2376'`: Maps port 2376 of the host to port 2376 of the container.

  This block defines the jenkins-blueocean service. Here's a breakdown of each line:
- `build`: Specifies that an image should be built for this service.
- `context: .`: Sets the build context to the current directory.
- `dockerfile`: Dockerfile: Specifies the Dockerfile to use for building the image.
- `image: myjenkins-blueocean:2.401.1-1`: Sets the name and tag of the image to be built.
- `container_name: jenkins-blueocean`: Sets the name of the container to jenkins-blueocean.
- `restart: on-failure`: Configures the container to automatically restart on failure.
- `networks`: Specifies the networks to which this service will be connected.
- `- jenkins`: Refers to the jenkins network defined later in the file.
- `environment`: Defines environment variables for the container.
- `- DOCKER_HOST=tcp://docker:2376`: Sets the DOCKER_HOST environment variable to tcp://docker:2376.
- `- DOCKER_CERT_PATH=/certs/client`: Sets the DOCKER_CERT_PATH environment variable to /certs/client.
- `- DOCKER_TLS_VERIFY=1`: Sets the DOCKER_TLS_VERIFY environment variable to 1.
- `ports`: Exposes ports from the container to the host.
- `- '8080:8080'`: Maps port 8080 of the host to port 8080 of the container.
- `- '50000:50000'`: Maps port 50000 of the host to port 50000 of the container.
- `volumes`: Mounts volumes to the container.
- `- jenkins-data:/var/jenkins_home`: Mounts the jenkins-data volume to the /var/jenkins_home directory in the container.
- `- jenkins-docker-certs:/certs/client:ro`: Mounts the jenkins-docker-certs volume to the /certs/client directory in the container in read-only mode.
- `networks: jenkins:`: This section defines a network named jenkins that will be used by the services.
- `volumes`: This section defines two volumes: jenkins-data and jenkins-docker-certs. If the volumes are marked as external, this indicates that they were created outside of this Docker Compose file.


#### Create credentials
For the step that publishes the image to Docker Hub we'll need to create a new credential in Jenkins:
- In Docker Hub, click your profile icon in the top-right and choose`Account Settings`, use the
  left sidebar to access the `Security` tab. Click the blue `New Access Token` button to create
  a Personal Access Token. Note down the token key that’s displayed as you won’t be able to
  recover it in the future.
- On Jenkins, you need to create a new credential with your Docker Hub account details.
  Click on in the arrow following your name (top right), go to Credentials > Global > Add
  credentials, and fill out the form with your username and password (the token we have just
  created). Fill in ID and Descriptions. The ID is import because it is how we can refer to this
  credential in the future.


#### Install "Blue Ocean" plugin
Install the "Blue Ocean" plugin: Jenkins > manage Jenkins > plugins > available plugins

#### Install "HTML Publisher" plugin
Install the "HTML Publisher" plugin: Jenkins > manage Jenkins > plugins > available plugins

To run the pipeline in Jenkins, do the following steps:

- Commit and push the Jenkinsfile to the remote repository.
- Create a new `Pipeline` Job in Jenkins.
- In the Configuration, select `Pipeline script from SCM`.
- Select `Git`, enter the URL of the repository.
- Enter the path to the Jenkinsfile from the root of your repository(devops/jenkins/Jenkinsfile).

#### Step 4:
The purpose of this part is to demonstrate the steps required to switch from the H2 in-memory database to MariaDB as the
persistent database solution. We have made the necessary changes in the application configuration files to seamlessly integrate MariaDB into the project.

To configure the project to use MariaDB, follow these steps:

1.  The project requires the MariaDB Java Client library. To add the dependency, update your `pom.xml` file:

```bash
<dependency>
 <groupId>org.mariadb.jdbc</groupId>
 <artifactId>mariadb-java-client</artifactId>
 <version>3.1.4</version>
</dependency>
```

2. Open the `application.properties` file and update the following properties:

```bash
server.port = 8080      

spring.datasource.driverClassName=org.mariadb.jdbc.Driver
spring.datasource.username=switch
spring.datasource.password=switch
spring.datasource.url=jdbc:mariadb://database:3306/g4db
```
- `server.port = 8080` This property sets the port number for the server to 8080. It indicates that the application will be accessible on port 8080.
- `spring.datasource.driverClassName=org.mariadb.jdbc.Driver` This property specifies the driver class name for MariaDB.
- `spring.datasource.username=switch` & `spring.datasource.password=switch` These properties define the username and password to connect to the MariaDB database.
- `spring.datasource.url=jdbc:mariadb://database:3306/g4db` This property sets the JDBC URL for connecting to the MariaDB database.
  In this case, the host is set to "database" (which might be the hostname or IP address of the database server),
  the port is set to 3306 (the default port for MariaDB), and the database name is set to "g4db".

These properties define the database connection details and instruct the application to use MariaDB.

3. In the `docker-compose.yml` file, you'll find the service configuration for the database:

```bash
services:
  database:
    image: mariadb
    restart: always
    environment:
      MARIADB_ROOT_PASSWORD: admin
      MARIADB_USER: switch
      MARIADB_PASSWORD: switch
      MARIADB_DATABASE: g4db
    networks:
      - g4Network
    ports:
      - '3306:3306'
```
- `image: mariadb`: Specifies the Docker image to use for the database service. In this case, it is using the "mariadb" image.
- `restart: always`: Sets the restart policy for the container to "always". This means that if the container stops or crashes, Docker will automatically restart it.
- `environment`: Defines environment variables that are passed to the container. The following environment variables are set:

  `MARIADB_ROOT_PASSWORD: admin`: Sets the root password for the MariaDB database to "admin". This password is used to authenticate administrative tasks.
  `MARIADB_USER: switch`: Sets the username for a non-root user in the MariaDB database to "switch".
  `MARIADB_PASSWORD: switch`: Sets the password for the non-root user to "switch".
  `MARIADB_DATABASE: g4db`: Creates a database named "g4db" in the MariaDB instance.
- `networks`: Specifies the networks the container should be connected to. In this case, the container is connected to a network named "g4Network".
- `ports`: Maps the container's ports to the host machine. The format '3306:3306' means that port 3306 of the host machine
  is being mapped to port 3306 exposed by the container (MariaDB's default port).


#### Step 5:

PhpMyAdmin is a free and open-source administration tool for viewing and managing MariaDB database.
It allows to execute SQL statements directly through the user interface.

To configure the project to use PhpMyAdmin, follow these steps:

1. In your Docker-compose.yml, right after the implementation of the MariaDB database, insert the following content:
```bash
   phpmyadmin:
    image: phpmyadmin
    restart: always
    environment:
      PMA_HOST: database
    networks:
      - g4Network
    ports:
      - '8081:80'
```

- `image: phpmyadmin`: Specifies the Docker image to use for the PHPMyAdmin container.
- `restart: always`: This line ensures that whenever the container is terminated, it will automatically restart.                                                                                                                                                                                                                                                                                                                                  |
- `environment: PMA_HOST: database`: Environment variables are values that can be accessed inside the container at runtime. They can be used to configure and customize the behavior of services.
-  `networks: - g4Network` : Specifies the networks the container should be connected to.
- `ports: 8081:80`: This is the port to access PhpMyAdmin.

#### Step 6:

```bash
frontend:
image: 'ruipppinho/g4:frontend40'
networks:
- g4Network
ports:
- '3000:3000'
```
- `frontend`: This is the name of the service.
- `image`: 'ruipppinho/g4:frontend40': Specifies the Docker image to use for the service. The image name is ruipppinho/g4 and the tag is frontend40.
- `networks`: Specifies the networks to which this service will be connected.
- `g4Network`: Refers to the g4Network network defined elsewhere in the Docker Compose file.
  ` ports`: Exposes ports from the container to the host.
- `'3000:3000'`: Maps port 3000 of the host to port 3000 of the container. This allows accessing the service running inside the container on port 3000 via the host machine.

```bash
backend:
image: 'ruipppinho/g4:backend40'
environment:
spring.datasource.url: jdbc:mariadb://database:3306/g4db
spring.datasource.driver-class-name: org.mariadb.jdbc.Driver
networks:
- g4Network
ports:
- '8080:8080'
depends_on:
- database
```
- `backend`: This is the name of the service.
- `image: 'ruipppinho/g4:backend40'`: Specifies the Docker image to use for the service. The image name is ruipppinho/g4 and the tag is frontend40.
- `environment`: Defines environment variables for the service.
- `spring.datasource.url: jdbc:mariadb://database:3306/g4db`: Sets the spring.datasource.url environment variable to the JDBC URL for connecting to the MariaDB database. The URL jdbc:mariadb://database:3306/g4db indicates that the service should connect to a database service named database on port 3306 using the database g4db.
- `spring.datasource.driver-class-name: org.mariadb.jdbc.Driver`: Sets the spring.datasource.driver-class-name environment variable to specify the JDBC driver class for MariaDB.
- `networks: g4Network`: Specifies the networks to which this service will be connected. Refers to the g4Network network defined elsewhere in the Docker Compose file.
- `ports:'8080:8080'`: Exposes ports from the container to the host. Maps port 8080 of the host to port 8080 of the container. This allows accessing the service running inside the container on port 8080 via the host machine.
- `depends_on: database`: Defines dependencies between services. Will ensure that the database service is up and running before the backend container is instantiated.



## Conclusion