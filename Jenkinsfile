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
        stage('sonar scan') {
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps{
                withSonarQubeEnv('sonar-local'){
                    bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=deploy-back -Dsonar.host.url=http://localhost:9000 -Dsonar.login=1005082a523fe3dcac5523bc532e6f7eba70675c -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**src/test/**,**/model/**,**Application.java**"
                }
            }
        }
        stage('Quality gate') {
            steps{
                timeout(time: 1, unit: 'MINUTES'){
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}

