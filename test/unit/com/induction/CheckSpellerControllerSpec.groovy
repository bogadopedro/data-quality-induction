package com.induction

import grails.test.mixin.TestFor
import groovy.mock.interceptor.MockFor
import org.junit.Test
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(CheckSpellerController)
class CheckSpellerControllerSpec extends Specification {


    public static final String SENTENCE = "hola mercadolibre"

    @Test
    void shouldCallCheckSentenceIfSentencePropertyIsPresent() {
        given:
            def response
            def checkSpellerServiceMock = mockFor(CheckSpellerService)
            checkSpellerServiceMock.demand.checkSentence() { String sentence -> return new CheckSentenceResult() }
            controller.checkSpellerService = checkSpellerServiceMock.createMock()
        when:
            controller.request.method = "POST"
            controller.request.JSON = ['sentence': SENTENCE]

            response = controller.checkSentence()

        then:
            assertNotNull(response)
            assertEquals(response['status'], 200)
    }

    @Test
    void shouldReturnBadRequestWhenSentencePropertyIsMissing() {
        given:
            def response
        when:
            controller.request.method = "POST"

            response = controller.checkSentence()

        then:
            assertNotNull(response)
            assertEquals(response['status'], 400)

    }

    @Test
    void shouldCallCheckSentenceWhenCheckQuestionIsCalled() {
        given:
            def response
            def checkSpellerServiceMock = mockFor(CheckSpellerService)
            checkSpellerServiceMock.demand.checkQuestions() { -> return new ArrayList<>() }
            controller.checkSpellerService = checkSpellerServiceMock.createMock()
        when:
            controller.request.method = "POST"
            response = controller.checkQuestions()

        then:
            assertNotNull(response)
            assertEquals(response['status'], 200)
    }

    @Test
    void shouldReturnMethodNotAllowedWhenForGetMethod() {
        given:
            Exception exception
        when:
        try {
             controller.methodNotAllowed()
        } catch (Exception e) {
            exception = e
        }
        then:
            assertNotNull(exception)
            assertEquals(exception['status'], 405)
    }

}
