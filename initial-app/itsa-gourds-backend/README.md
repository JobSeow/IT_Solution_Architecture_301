# Back-End For ITSA-Gourds E-Commerce Site

## Running Backend locally
``` bash
1. Make sure docker is installed and running
2. cd into "initial-app/itsa-gourds-backend" folder
3. Run "./gradlew build" to build jar file
4. Dockerfile will copy the jar file into the dockerised file
   + Default port for the backend application is 8080
   + Changes can be made in "Dockerfile"
5. Run ".\build-docker.sh"
6. Run " .\run-docker.sh "
7. Backend should be running on #localhost:8080

```