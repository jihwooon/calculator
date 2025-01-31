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
              - name: builder
                image: jenkins/inbound-agent
                command:
                - cat
                tty: true
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
                    container('builder') {
                        sh "./gradlew compileJava"
                    }
                }
            }
        }
    }
}
