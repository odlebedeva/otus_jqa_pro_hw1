timeout(4) {
    node("maven") {
        wrap([$class: 'BuildUser']) {
            currentBuild.description = """
                build user: $BUILD_USER
                branch: $BRANCH
            """

            config = readYaml text: env.YAML_CONFIG ?: null;

            if (config != null) {
                for (param in config.entrySet()) {
                    env."${param.getKey()}" = param.getValue()
                }
            }
        }

        stage("Checkout") {
            checkout scm;
        }

        stage("Create configuration") {
            sh """
                echo BROWSER_NAME=${env.BROWSER_NAME} > ./.env
                echo BROWSER_VERSION=${env.BROWSER_VERSION} >> ./.env
                echo BASE_URL=${env.BASE_URL} >> ./.env
                echo REMOTE_URL=${env.REMOTE_URL} >> ./.env
                echo DRIVER_TYPE=${env.DRIVER_TYPE} >> ./.env
            """
        }

        stage("Run UI tests") {
            sh """
                #!/bin/sh
                export PATH=$PATH:/usr/bin:/usr/local/bin
                docker --version
                mkdir ./reports
                docker run --rm --env-file ./.env -v ./reports:/root/ui_tests_allure-results -t ui_tests:${env.getProperty('TEST_VERSION')}
            """
        }

        stage("Publish allure results") {
            REPORT_DISABLE = Boolean.parseBoolean(env.getProperty('REPORT_DISABLE')) ?: false
            allure([
                    reportBuildPolicy: 'ALWAYS',
                    results: ["./reports", "./allure-results"],
                    disabled: REPORT_DISABLE
            ])
        }
    }
}