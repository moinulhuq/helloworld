def gv

pipeline {
    agent any
    
    parameters { 
        string(name: 'VERSION', defaultValue: '', description: 'version to deploy')
        choice(name: 'CHOICES', choices: ['one', 'two', 'three'], description: '')
        booleanParam(name: 'test', defaultValue: true, description: '')        
    }
    
    stages {

        stage('checkout') {
            steps {
                git 'git@github.com:moinulhuq/helloworld.git'
            }
        }

        stage('init') {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }

        stage('build') {
            steps {
                script {
                    gv.buildApp()
                }
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
