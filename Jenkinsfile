pipeline {
    agent any

    tools {
        jdk "11"
        gradle "Gradle 7.5"
    }

     stages {
          stage('Build') {
              steps {
                  sh './gradlew build'
              }
          }
          stage('Test') {
              steps {
                  sh './gradlew check'
              }
          }
     }
}
