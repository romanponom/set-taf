pipeline {
    agent {
        label 'master'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
    }
    agent {
        docker {
            image 'aerokube/selenoid:latest-release'
            args '-d --name selenoid -p 4444:4444 -v //var/run/docker.sock:/var/run/docker.sock -v ${WORKSPACE}:/etc/selenoid:ro'
        }
    }

}