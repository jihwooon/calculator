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
                image: sheayun/jnlp-agent-sample
                env:
                - name: DOCKER_HOST
                  value: "tcp://localhost:2375"
              - name: dind
                image: sheayun/dind
                command:
                - /usr/local/bin/dockerd-entrypoint.sh
                env:
                - name: DOCKER_TLS_CERTDIR
                  value: ""
                  securityContext:
                    privileged: true
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
        stage('Static Analysis') {
            steps {
                script {
                    container('builder') {
                        sh "./gradlew checkstyleMain"
                        publishHTML(target: [
                          reportDir: 'build/reports/checkstyle',
                          reportFiles: 'main.html',
                          reportName: 'Checkstyle Report'
                        ])
                    }
                }
            }
        }
    }
}
