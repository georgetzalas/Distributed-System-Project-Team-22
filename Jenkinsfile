pipeline {

agent any

environment {
        DOCKER_TOKEN=credentials('docker-push-secret')
        DOCKER_USER='georgetzalas'
        DOCKER_SERVER='ghcr.io'
        DOCKER_PREFIX='ghcr.io/georgetzalas/spring'
    }


stages {

    stage('run ansible pipeline') {
        steps {
            build job: 'ansible'
        }
    }

    stage('Create .mvn/properties') {
        steps {
            sh '''
                echo "Start creating .mvn/properties"
                ./mvnw test
                echo "Adding distribution url"
                echo 'distributionUrl=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.8.8/apache-maven-3.8.8-bin.zip' > .mvn/wrapper/maven-wrapper.properties
            '''
        }
    }

    stage('Test') {
        steps {
            sh '''
                echo "Start testing"
                ./mvnw test
            '''
        }
    }

    stage('Docker build and push') {
            steps {
                sh '''
                    HEAD_COMMIT=$(git rev-parse --short HEAD)
                    TAG=$HEAD_COMMIT-$BUILD_ID
                    docker build --rm -t $DOCKER_PREFIX:$TAG -t $DOCKER_PREFIX:latest -f Dockerfile .
                '''

                sh '''
                    echo $DOCKER_TOKEN | docker login $DOCKER_SERVER -u $DOCKER_USER --password-stdin
                    docker push $DOCKER_PREFIX --all-tags
                '''
            }
        }



    stage('deploy to kubernetes') {
            steps {
                sh '''
                    HEAD_COMMIT=$(git rev-parse --short HEAD)
                    TAG=$HEAD_COMMIT-$BUILD_ID
                    export ANSIBLE_CONFIG=~/workspace/ansible/ansible.cfg
                    ansible-playbook -i ~/workspace/ansible/hosts.yaml -e new_image=$DOCKER_PREFIX:$TAG ~/workspace/ansible/playbooks/k8s-update-spring-deployment.yaml
                '''
            }
        }

//    post {
//        always {
//            mail  to: "tsadimas@gmail.com", from: "tsadimas@gmail.com", body: "Project ${env.JOB_NAME} <br>, Build status ${currentBuild.currentResult} <br> Build Number: ${env.BUILD_NUMBER} <br> Build URL: ${env.BUILD_URL}", subject: "JENKINS: Project name -> ${env.JOB_NAME}, Build -> ${currentBuild.currentResult}"
//        }
//    }

}

}
