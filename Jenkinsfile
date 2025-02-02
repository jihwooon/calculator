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
                checkout scm
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
        stage('Unit Test') {
            steps {
                script {
                    container('builder') {
                        sh "./gradlew test"
                        publishHTML(target: [
                          reportDir: 'build/reports/tests/test',
                          reportFiles: 'index.html',
                          reportName: 'Junit Report'
                        ])
                    }
                }
            }
        }
        stage('Code Coverage') {
            steps {
                script {
                    container('builder') {
                        sh "./gradlew jacocoTestReport"
                        publishHTML(target: [
                          reportDir: 'build/reports/jacoco/test/html',
                          reportFiles: 'index.html',
                          reportName: 'JaCoCo Report'
                        ])
                        sh "./gradlew test jacocoTestCoverageVerification"
                   }
               }
            }
        }
    }
}
