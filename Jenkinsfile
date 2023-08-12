pipeline {
    agent none

    stages {

        stage("Build jar & copy dependencies") {

            agent {
                docker {
                    image "maven:3-alpine"
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                sh "mvn clean package dependency:copy-dependencies -DskipTests -DoutputDirectory=%cd%/target/lib"
            }
        }

        stage("Build image") {
            steps {
                script {
                    app = docker.build("rhuayhua/selenium-parabank")
                }
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