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
               sh "sudo docker build -t=rhuayhua/selenium-parabank ."
            }
        }

        stage("Push image") {
            steps {
                script {
//                     withDockerRegistry(credentialsId:'dockerhub_cred') {
//                          sh "sudo docker push rhuayhua/selenium-parabank:latest"
//                     }
                    def dockerImage = docker.build("rhuayhua/selenium-parabank:latest")
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub_cred') {
                        dockerImage.push()
                    }

                }
            }
        }
    }

}