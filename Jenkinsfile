pipeline {
    environment {
        dockerImageName = "jihwooon/jenkins-in-actions"
    }
    agent {
        kubernetes {
            yaml'''
            apiVersion: v1
            kind: Pod
            spec:
              containers:
              - name: jnlp
                image: jenkins/inbound-agent
            '''
        }
    }
    tools {
      jdk 'default-jdk'
    }
    stages {
        stage('Checkout') {
            steps {
              git url: 'git@github.com:jihwooon/calculator.git'
                  branch: 'main',
                  credentialsId: 'github-credentials'
            }
        }
        stage('Compile') {
            steps {
                script {
                  sh "./gradlew compileJava"
                }
            }
        }
    }
}
