pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIAL = credentials('dockerhub_cred')
    }

    stages {

        stage("Build jar & copy dependencies") {
            steps {
                sh "mvn clean package dependency:copy-dependencies -DskipTests -DoutputDirectory=target/lib"
            }
        }

        stage("Build image") {
            steps {
               sh "sudo docker build -t=rhuayhua/selenium-parabank ."
            }
        }

//         stage("Push image") {
//             steps {
//                 withRegistry([usernamePassword(credentialsId:'dockerhub_cred', passwordVariable:'pass', usernameVariable:'user')]) {
//                      sh "sudo docker push rhuayhua/selenium-parabank:latest"
//                 }
//             }
//         }
    }

}