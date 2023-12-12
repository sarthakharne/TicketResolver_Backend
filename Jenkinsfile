pipeline {
     environment{
        dockerimage=""
    }
    agent any
    stages {
        stage('Git clone') {
            steps {
            git branch: 'main',url: 'https://github.com/nirajlande/SPE-Backend.git'
            }
        }

        stage("Running Test cases"){
            steps{
                sh "mvn clean test"
            }
        }

        stage("Maven Build"){
            steps{
                sh "mvn clean install"
            }
        }

        stage('Docker Build Image') {
            steps {
                script{
                    dockerimage=docker.build "nirajlande/spe-backend"
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script{
                    docker.withRegistry('','dockerhub'){
                    dockerimage.push()
                    }
                }
            }
        }

        stage("Removing Image from local"){
            steps{
                sh "docker rmi nirajlande/spe-backend"
            }
        }

    }
}
