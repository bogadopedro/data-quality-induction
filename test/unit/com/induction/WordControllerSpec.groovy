package com.induction

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(WordController)
@Mock(Word)
class WordControllerSpec extends Specification {

    public static final String WORD = "Messi"

    void "test should call add word"() {

        given:
            def response

        when:
            controller.request.method = "POST"
            controller.request.JSON = ['word': WORD]
            response = controller.addWord()

        then:
            assertNotNull(response)
            assertEquals(response['status'], 200)
    }

    void "test should return Bad request 400 when the word param is missing"() {

        given:
            def response
        when:
            controller.request.method = "POST"
            response = controller.addWord()

        then:
        assertNotNull(response)
        assertEquals(400, response['status'])
    }
}
