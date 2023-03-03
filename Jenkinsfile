pipeline {
    agent any

    stages {
        stage('Build Maven') {
            steps {
                sh 'mvn clean install'

            }
        }
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t helloworld:${BUILD_NUMBER} .'
                    sh 'docker build -t helloworld:latest .'
                }
            }
        }
    }
}
