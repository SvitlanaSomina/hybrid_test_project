pipeline {
    agent any

    tools {
        jdk "11"
        gradle "7.5"
    }

    stages {
        stage('ui_tests') {
          steps {
              sh './gradlew clean test --no-daemon'
           }
        }
    }
}