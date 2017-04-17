package com.induction

import grails.test.mixin.TestFor

/**
 * Created by pbogado on 12/4/17.
 */
@TestFor(CheckSpellerController)
class CheckSpellerControllerTest extends GroovyTestCase {

    public static final String SENTENCE = "hola mercadolibre"

    void testCheckQuestions() {
        given:
            def response
            CheckSentenceResult sentenceExpectedResult = new CheckSentenceResult()
            sentenceExpectedResult.quantityOfError = 1
            sentenceExpectedResult.errorWords.add("mercadolibre")
        when:
            controller.request.method = "POST"
            controller.request.JSON = ['sentence': SENTENCE]

            response = controller.checkSentence()

        then:
            assertNotNull(response)
            assertEquals(response['status'], 200)
            assertEquals(sentenceExpectedResult, response['response'])
    }
}
