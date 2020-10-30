pipeline {
    agent none

    stage('Checkout') {
        agent {label 'master'}
        steps {
            checkout scm
        }
    }
    stage('Build image') {
        agent {
            docker {
                image 'aerokube/selenoid:latest-release'
                args '-d --name selenoid -p 4444:4444 -v //var/run/docker.sock:/var/run/docker.sock -v ${WORKSPACE}/ui-auto/src/main/resources/selenoid/config:/etc/selenoid:ro'
            }
        }
        steps {
            sh 'gradle clean test'
        }
    }
}