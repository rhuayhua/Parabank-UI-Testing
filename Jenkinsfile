pipeline {
    agent any

    stages {

        stage("Build jar & copy dependencies") {
            steps {
                sh "mvn clean package dependency:copy-dependencies -DskipTests -DoutputDirectory=target/lib"
            }
        }

        stage("Build image") {
            steps {
                script {
                     def dockerImage = docker.build("rhuayhua/selenium-parabank:latest")
                }
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