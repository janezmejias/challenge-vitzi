# backend-app

## Installation

C3 Integrations requires [JDK11](https://docs.aws.amazon.com/en_en/corretto/latest/corretto-11-ug/downloads-list.html)
to run.

Install the dependencies and devDependencies and start the server.

### Clone with GitHub CLI

```sh
gh repo clone vitzisoftwareorganisation/vitzi-integration
```

### build docker image - back
```sh
eval $(minikube docker-env)

docker build -t categories:1.0 .
docker tag categories:1.0 vitzi/categories:1.0

# Optional
docker run -dp 8080:8080 categories
```

### build docker image - front
```sh
eval $(minikube docker-env)

docker build -t vz-front:1.0 .
docker tag vz-front:1.0 vitzi/vz-front:1.0

# Optional
docker run -dp 1234:80 vz-front:1.0 
```

### Deploy (Kubernetes - Linkerd )
```sh
cat deployment-dev.yml | linkerd inject - | kubectl apply -f -

```

### Visualization (viz)
```sh
linkerd viz dashboard &
```


### application.properties --stage --prod

```sh
spring.application.name=qrvey_integration
#spring.profiles.active=stage
#spring.profiles.active=prod
spring.config.import=optional:configserver:http://ae9223bb376a34f73ae7a172677a5f98-1932667953.us-west-2.elb.amazonaws.com
```

### Install

```sh
mvn clean install -DskipTests -s settings.xml
```

## Run --dev

To run qrvey-integration start the server with:

```sh
mvn spring-boot:run -s settings.xml
```

## Run Test

Requirements: Maven 3.x and JDK 11. Due to wrong formatting of console text messages in Maven Version prior to 3.1.0 it
is highly recommended to use Maven 3.1.0 or higher.

```sh
mvn surefire:test -Dtest=GlobalTest 

Results :
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
```

## Image

C3 Integrations is very easy to install and deploy in a Docker container.

By default, the Docker will expose port 8181, so change this within the Dockerfile if necessary. When ready, simply use
the Dockerfile to build the image.

```sh
docker build -t vitzi-integration:1.0 .
#docker tag vitzi-integration:1.0 janezmejias09/vitzi-integration:1.0
#docker push janezmejias09/vitzi-integration:1.0
docker run -p 8181:8080 -td --name vitzi-dp vitzi-integration:1.0
```
