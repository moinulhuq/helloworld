pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                mvn install
		docker build -t helloworld:${BUILD_NUMBER} .
		docker build -t helloworld:latest .
            }
        }
    }
}

