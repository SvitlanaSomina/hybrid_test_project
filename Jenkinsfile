pipeline {
    agent any

    tools {
        jdk "11"
        gradle "Gradle 7.5"
    }

     triggers {
         cron('0 9 * * *')
     }

     stages {
          stage('Test') {
              steps {
                  bat './gradlew test'
              }
          }
     }

     post {
         always {
             allure([
              reportBuildPolicy : 'ALWAYS',
              results : [[path : 'build/allure-results']]
             ])
          }
     }
}
