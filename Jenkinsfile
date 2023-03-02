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
                    withCredentials([usernamePassword(credentialsId: 'DockerLogin', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]){
                    sh 'docker login -u ${USERNAME} -p ${PASSWORD}'
		        }
                    sh 'docker tag helloworld:${BUILD_NUMBER} moinulhuq/helloworld:${BUILD_NUMBER}'
                    sh 'docker tag helloworld:latest moinulhuq/helloworld:latest'
                    sh 'docker push moinulhuq/helloworld:${BUILD_NUMBER}'
                    sh 'docker push moinulhuq/helloworld:latest'
                }
            }
        }
/*
        stage('Deploy to k8s'){
            steps{
                script{
                    kubernetesDeploy (configs: 'deploymentservice.yaml',kubeconfigId: 'k8sconfigpwd')
                }
            }
        }
*/
    }
}

