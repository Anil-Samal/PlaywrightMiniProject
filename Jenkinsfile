pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3.9'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Clean') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }
    }

    post {
        always {
            junit '**/surefire-reports/*.xml'

            archiveArtifacts artifacts: 'test-results/**/*', fingerprint: true

            archiveArtifacts artifacts: 'allure-results/**/*', fingerprint: true

            archiveArtifacts artifacts: 'playwright-report/**/*', fingerprint: true
        }
    }
}