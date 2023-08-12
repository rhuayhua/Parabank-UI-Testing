#!/usr/bin/env sh

# Env variables :
# $ExecutionType=Selenium-Grid
# $BrowserName=Chrome
# $groups=Regression
# $HUB_HOST=hub
# $MODULE -> testsuite.xml

echo ">>>  Checking if hub[$HUB_HOST] is ready"
ready=`curl -s http://$HUB_HOST:4444/status | jq -r .value.ready`
echo ">>> is it ready??? -> $ready"

while [ "$ready" != "true" ]
do
    echo ">>> sleeping 1 sec ...."
    sleep 1
    ready=`curl -s http://$HUB_HOST:4444/status | jq -r .value.ready`
done

# start the java command (same command as entrypoint in image)
java -javaagent:"lib/aspectjweaver-1.9.4.jar" -classpath "selenium-docker.jar:selenium-docker-tests.jar:lib/*" \
     -DExecutionType=$ExecutionType \
     -DBrowserName=$BrowserName \
     -Dgroups=$groups \
     -DHUB_HOST=$HUB_HOST \
      org.testng.TestNG $MODULE