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
                    def mavenPom = readMavenPom file: "pom.xml"
                    nexusArtifactUploader artifacts: [
                        [artifactId: 'helloworld', 
                        classifier: '', 
                        file: "target/helloworld-${mavenPom.version}.jar", 
                        type: 'jar'
                        ]
                    ], 
                    credentialsId: 'Jenkins-Nexus', 
                    groupId: 'com.example', 
                    nexusUrl: '172.31.31.117:8081', 
                    nexusVersion: 'nexus3', 
                    protocol: 'http', 
                    repository: 'helloworld-jenkins', 
                    version: "${mavenPom.version}"
                }
            }
        }
        stage('Deploy Kubenetes'){
            steps{
                script{
                    withCredentials([file(credentialsId: 'kubeconfig', variable: 'FILE')]){
                    sh 'kubectl apply -f /manifest/nginx-pod.yaml'
		        }
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
