bigqueue {
    disabled = false

    // for single cluster connection
    connectionPool {
        baseUrl = "http://localhost:8081" //MANDATORY!
        resourcesNumber = 100
        repairThreadsNumber = 10
    }

    // for multi cluster connection
    connectionPools {
        test {
            baseUrl = "http://localhost:8081" //MANDATORY!
            resourcesNumber = 100
            repairThreadsNumber = 10
        }
        test1 {
            baseUrl = "http://localhost:8081" //MANDATORY!
            resourcesNumber = 100
            repairThreadsNumber = 10
        }
    }

    consumers {
        startDelay = 5
        sleepOnEmpty = 1
    }

    producers {
        retryCount = 3
        retryTimeout = 100
    }

    omitInstaceRestClientPools = false
    noticeErrorToNewRelic = false
}