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
                script{
                    def mavenPomVersion = readMavenPom file: "pom.xml"
                    nexusArtifactUploader artifacts: [
                        [artifactId: 'helloworld', 
                        classifier: '', 
                        file: 'target/helloworld-0.0.2.jar', 
                        type: 'jar'
                        ]
                    ], 
                    credentialsId: 'Jenkins-Nexus', 
                    groupId: 'com.example', 
                    nexusUrl: '172.31.31.117:8081', 
                    nexusVersion: 'nexus3', 
                    protocol: 'http', 
                    repository: 'helloworld-jenkins', 
                    version: '0.0.2'
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
