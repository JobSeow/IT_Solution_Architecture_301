
<h1 align="center">
    <img src="img/../initial-app/itsa-gourds/assets/images/logo.png" width="200"> 
    <br>
        ITSA Gourds Mask E-Commerce
    <br>
</h1>

<h4 align="center">The best site to get a mask in times of crisis.</h4>

<p align="center">
  <a href="#file-structure">File Structure</a> •
  <a href="#screenshots-of-application">Screenshots of Application</a> •
  <a href="#installation-options">Installation Options</a> •
  <a href="#configuration-options">Configuration Options</a> 
</p>


# File Structure

- Proposal
  - This directory contains all the documents required for submission 
- initial-app
  - This directory contains the project application
  
 # Screenshots of application
#### Login Page
![Login page](https://github.com/cs301-itsa/project-2019-20t2-gourds/blob/master/proposal/screenshots/login.png)

#### Add Mask Page
![Add mask page](https://github.com/cs301-itsa/project-2019-20t2-gourds/blob/master/proposal/screenshots/add.png)

#### Masks Page
![Masks page](https://github.com/cs301-itsa/project-2019-20t2-gourds/blob/master/proposal/screenshots/masks.png)

#### Mask detail Page
![Mask detail page](https://github.com/cs301-itsa/project-2019-20t2-gourds/blob/master/proposal/screenshots/mask-details.png)

#### Checkout Page
![Checkout page](https://github.com/cs301-itsa/project-2019-20t2-gourds/blob/master/proposal/screenshots/cart.png)


# Installation Options
* Download files from GitHub

    OR

* Git Clone directory

# Configuration Options


## Preparation Before deploying Front End 
1. Make sure [node is installed](https://nodejs.org/en/download/)

2. Change endpoint url for backend apis if necessary
   + In "sample.env"
      + BASE_URL=\<Enter domain here>

3. Change directory to "itsa-gourds"
   + `npm run install`
   + `npm run generate`
  
4. Push changes to git and it will be testing and deployed with Travis

<br>

## Preparation Before Deploying Back End
1. cd into "initial-app/itsa-gourds-backend" folder

2. Run `./gradlew build` to build jar file

3. Dockerfile will copy the jar file into the dockerised file
   + Default port for the backend application is 8080
   + Changes can be made in "Dockerfile"

4. Push changes to git and it will be testing and deployed with Travis


<br>

## Editing Travis
Travis CI is used for automated testing and deploying to AWS

In order to setup Travis for use with AWS, 

1. Connect Travis with your GitHub account

2. In .travis.yml file
   1. script (line 5) checks gradle to automatically run tests
      + cd ../../ returns to the original directory in order to deploy application
   2. stages
      + Sets the condition of deployment for branch
      + Rename the name of each stage if needed
      + Set conditions for deployment jobs with "if: branch = "
   3. jobs
      + Deploys the front end and back end sequentially
      + Set up provider settings 
         + Refer to: [travis deployment](https://docs.travis-ci.com/user/deployment/s3/)
         + Hide secret access key with environment variables by inputting them in [travis environment variables ](https://docs.travis-ci.com/user/customizing-the-build)

3. Push changes to git and it will be testing and deployed with Travis

4. Monitor deployment status on travis dashboard

<br>

## Running the application locally
Please refer to the individual README files in "itsa-gourds" and "itsa-gourds-backend"