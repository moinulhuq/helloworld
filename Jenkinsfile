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
        stage('Fingerprint'){
            steps{
                script{
                    fingerprint 'target/*.jar'
                }
            }
        }
        stage('Archive Artifacts'){
            steps{
                script{
                    archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false
                }
            }
        }
        stage('Upload to Nexsus'){
            steps{
		echo 'Upload to Nexsus'
            }
        }
        stage('Deploy Kubenetes'){
            steps{
                script{
                    withCredentials([file(credentialsId: 'kubeconfig', variable: 'FILE')]){
                    sh 'kubectl apply -f manifest/helloworld-deployment.yaml'
                    sh 'kubectl apply -f manifest/helloworld-service.yaml'
		        }
                }
            }
        }
    }
}
