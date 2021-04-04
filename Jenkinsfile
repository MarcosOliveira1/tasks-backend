pipeline {
    agent any
    stages{
        stage('build backend') {
            steps{
                bat 'mvn clean package -DskipTest=true'
            }
        }
    }
}
