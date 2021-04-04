pipeline {
    agent any
    stages{
        stage('build backend') {
            steps{
                bat 'mvn clean package -DskipTests=true'
            }
        }
        stage('unitis teste') {
            steps{
                bat 'mvn test'
            }
        }
    }
}
