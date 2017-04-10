import com.induction.rest.services.MockCuriosaMenteClient
import com.induction.rest.services.RESTCuriosaMenteClient
import com.induction.rest.services.RESTWordReferenceClient
import grails.plugins.rest.client.RestBuilder
import grails.util.Environment

// Place your Spring DSL code here
beans = {
    rest(RestBuilder) {}

//    restClient(RestClient) { bean ->
//
//        bean.initMethod = 'init'
//        bean.scope = 'singleton'
//
//        pools = [
//                "/spellchecker/.*"  : ref("curiosamentePool")
//        ]
////        caches  = [ '/spellchecker/.*': ref('curiosamenteCache') ]
//    }

//    defaultPool(FastHttpClientPool) { bean ->
//        bean.initMethod = 'init'
//        bean.destroyMethod = 'destroy'
//        bean.scope = 'singleton'
//        name = 'HTTP Pool - Curiosamente'
//
//        def defaultPoolConfig = grailsApplication.config.defaultPool
//
//        baseUrl= defaultPoolConfig.baseUrl
//        validationUri = '/trivia/'
//        timeBetweenValidation = 20000
//        soTimeout = 5000
//        defaultPoolWait = 100
//        resourcesNumber = 200
//        keepAlive = 300
//        repairThreadsNumber = 10
//        logTime = 60000
//        retryNumber = 3
////        useNaiveSSLVerification = true
//    }

    Environment.executeForCurrentEnvironment {

        production {
            curiosamenteClient(RESTCuriosaMenteClient) { bean ->
                bean.scope = "singleton"
            }

            wordReferenceClient(RESTWordReferenceClient) { bean ->
                bean.scope = "singleton"
            }

        }

        development {
//            curiosamenteClient(RESTCuriosaMenteClient) { bean ->
//                bean.scope = "singleton"
//                restClient = ref("restClient")
//            }
            curiosamenteClient(RESTCuriosaMenteClient) { bean ->
                bean.scope = "singleton"
                grailsApplication = ref("grailsApplication")
                rest = ref("rest")
            }

            wordReferenceClient(RESTWordReferenceClient) { bean ->
                bean.scope = "singleton"
                grailsApplication = ref("grailsApplication")
                rest = ref("rest")
            }
        }

        test {
            curiosamenteClient(MockCuriosaMenteClient) { bean ->
                bean.scope = "singleton"
            }
        }
    }
}

