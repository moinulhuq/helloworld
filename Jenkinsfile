pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn install'
		sh 'docker build -t helloworld:${BUILD_NUMBER} .'
		sh 'docker build -t helloworld:latest .'
            }
        }
    }
}

