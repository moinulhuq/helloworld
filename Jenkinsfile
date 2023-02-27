node {
    checkout scm
    def rootDir = pwd()
    def gv = load "${rootDir}/script.groovy"
}

pipeline {
    agent any
    parameters { 
        string(name: 'VERSION', defaultValue: '', description: 'version to deploy')
        choice(name: 'CHOICES', choices: ['one', 'two', 'three'], description: '')
        booleanParam(name: 'test', defaultValue: true, description: '')        
    }
    
    stages {


        stage('build') {
            steps {
                gv.buildApp()
            }
        }

        stage('test') {
            when {
                expression{
                    params.test == true
                }
            }
            steps {
                script {
                    gv.testApp()
                }
            }
        }
        stage('deploy') {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }

    }
}
