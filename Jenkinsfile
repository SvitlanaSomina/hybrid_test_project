pipeline {
    agent any

    tools {
        jdk "11"
        gradle "Gradle 7.5"
    }

     stages {
          stage('ui_tests') {
              steps {
                  sh './gradlew clean test'
              }
          }
     }
}
