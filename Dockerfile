FROM azul/zulu-openjdk-alpine:11

# run - to install packages for healthcheck
RUN apk update && apk add curl  && apk add jq && adduser -D ubuntu

# set user
USER ubuntu

# workspace
WORKDIR /home/ubuntu/parabank

# copy .jar under target from host to image
ADD target/selenium-docker-tests.jar selenium-docker-tests.jar
ADD target/selenium-docker.jar selenium-docker.jar
ADD target/lib lib

# copy suite files, and other dependency if necessary (.csv, .json, .xls)
ADD TestNG/testng_Landing.xml testng_Landing.xml
ADD TestData TestData

# add healthchesk script
ADD healthcheck.sh healthcheck.sh

# env variables
# $ExecutionType=Selenium-Grid
# $BrowserType=Chrome
# $groups=Regression
# $HUB_HOST=localhost
# $MODULE -> testsuite.xml

ENTRYPOINT ./healthcheck.sh
