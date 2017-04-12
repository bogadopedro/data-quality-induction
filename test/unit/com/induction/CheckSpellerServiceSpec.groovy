package com.induction

import com.fiftyonred.mock_jedis.MockJedis
import com.induction.rest.services.MockCuriosaMenteClient
import com.induction.rest.services.MockWordReferenceClient
import com.induction.rest.services.WordReferenceClient
import grails.test.mixin.TestFor
import redis.clients.jedis.Jedis
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(CheckSpellerService)
class CheckSpellerServiceSpec extends Specification {

    public static final String INCORRECT_SENTENCE = "hola mercadolibre"
    public static final String CORRECT_SENTENCE = "hola"

    Jedis jedis
    WordReferenceClient wordReferenceClient

    def setup() {
        jedis = new MockJedis("test")
        jedis.set("hola", "hola")

        wordReferenceClient = new MockWordReferenceClient()

        service.jedis = jedis
        service.wordReferenceClient = wordReferenceClient
        service.curiosamenteClient = new MockCuriosaMenteClient()
    }

    def cleanup() {
    }

    void "test should return one error for bad word"() {

        when:
            CheckSentenceResult checkSentenceResult = service.checkSentence(INCORRECT_SENTENCE)
        then:
            assertEquals(1, checkSentenceResult.getQuantityOfError())
    }

    void "test should return zero error for correct sentence"() {

        when:
            CheckSentenceResult checkSentenceResult = service.checkSentence(CORRECT_SENTENCE)
        then:
            assertEquals(0, checkSentenceResult.getQuantityOfError())
    }

    void "test should return 1 when incrementError is called just one"(){

        given:
            CheckSentenceResult checkSentenceResult = new CheckSentenceResult()
        when:
            checkSentenceResult.incrementError()
        then:
            assertEquals(1, checkSentenceResult.quantityOfError)

    }

    void "test should call curiosamente to get all question to check"(){
        given:
            List<CheckSentenceResult> resultList
        when:
            resultList = service.checkQuestions()
        then:
            assertNotNull(resultList)
    }
}
