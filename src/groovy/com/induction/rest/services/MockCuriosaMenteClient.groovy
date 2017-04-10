package com.induction.rest.services

import com.induction.Question
import org.apache.log4j.Logger
import org.codehaus.jackson.map.ObjectMapper

/**
 * Created by pbogado on 7/4/17.
 */
class MockCuriosaMenteClient implements CuriosaMenteClient{

    def mockedQuestions
    def log = Logger.getLogger(this.class)

    @Override
    def getAllQuestions() {

        log.info("Mocking questions")

        println "*************************************************************"
        println mockedQuestions
        println "*************************************************************"

        return new ObjectMapper().readValue(mockedQuestions, Question[].class)

    }

}
