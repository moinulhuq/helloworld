pipeline {
    agent any
    
    triggers {
    
        pollSCM '* * * * *'
    }
    
    stages {

        stage('build') {
            steps {
                sh "ls -la"
            }
        }

    }
}
