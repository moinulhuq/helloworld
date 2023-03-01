pipeline {
    agent any

    stages {
        stage('Build Maven') {
            steps {
                sh 'mvn install'

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
        stage('Push image to Hub'){
            steps{
                script{
                   withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                   sh 'docker login -u javatechie -p ${dockerhubpwd}'
		}
                   sh 'docker push javatechie/devops-integration'
                }
            }
        }
        stage('Deploy to k8s'){
            steps{
                script{
                    kubernetesDeploy (configs: 'deploymentservice.yaml',kubeconfigId: 'k8sconfigpwd')
                }
            }
        }	    
    }
}

