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

        archiveArtifacts(
            artifacts: 'screenshots/**/*',
            fingerprint: true,
            allowEmptyArchive: true
        )

        archiveArtifacts(
            artifacts: 'traces/**/*',
            fingerprint: true,
            allowEmptyArchive: true
        )

        archiveArtifacts(
            artifacts: 'videos/**/*',
            fingerprint: true,
            allowEmptyArchive: true
        )

    }
}