import com.induction.rest.services.MockCuriosaMenteClient
import com.induction.rest.services.MockWordReferenceClient
import com.induction.rest.services.RESTCuriosaMenteClient
import com.induction.rest.services.RESTWordReferenceClient
import grails.plugins.rest.client.RestBuilder
import grails.util.Environment
import redis.clients.jedis.Jedis
import com.fiftyonred.mock_jedis.MockJedis

// Place your Spring DSL code here
beans = {

    rest(RestBuilder) {}

    Environment.executeForCurrentEnvironment {

        production {
            jedis(Jedis, "localhost") {}

            curiosamenteClient(RESTCuriosaMenteClient) { bean ->
                bean.scope = "singleton"
            }

            wordReferenceClient(RESTWordReferenceClient) { bean ->
                bean.scope = "singleton"
            }

        }

        development {

            jedis(Jedis, "localhost") {}

            curiosamenteClient(MockCuriosaMenteClient) { bean ->
                bean.scope = "singleton"
            }

//            curiosamenteClient(RESTCuriosaMenteClient) { bean ->
//                bean.scope = "singleton"
//                grailsApplication = ref("grailsApplication")
//                rest = ref("rest")
//            }

            wordReferenceClient(RESTWordReferenceClient) { bean ->
                bean.scope = "singleton"
                grailsApplication = ref("grailsApplication")
                rest = ref("rest")
            }
        }

        test {

            jedis(MockJedis, "test") {}

            curiosamenteClient(MockCuriosaMenteClient) { bean ->
                bean.scope = "singleton"
            }
            wordReferenceClient(MockWordReferenceClient) { bean ->
                bean.scope = "singleton"
            }
        }
    }
}

