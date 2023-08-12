pipeline {
    agent none

    stages {

        stage("Build jar & copy dependencies") {

            steps {
                sh "mvn clean package dependency:copy-dependencies -DskipTests -DoutputDirectory=%cd%/target/lib"
            }
        }

        stage("Build image") {
            steps {
                sh "pwd"
                sh "docker build -t=rhuayhua/selenium-parabank ."
            }
        }

//         stage("Push image") {
//             steps {
//                 script {
//                     docker.withRegistry('https://registry.hub.docker.com','dockerhub_cred') {
//                         app.push("latest")
//                     }
//
//                 }
//             }
//         }
    }

}