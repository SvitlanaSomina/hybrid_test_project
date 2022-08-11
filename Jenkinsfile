pipeline {
    agent any

    triggers {
         cron('30 13 * * *')
    }

    tools {
        jdk "11"
        gradle "Gradle 7.5"
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
