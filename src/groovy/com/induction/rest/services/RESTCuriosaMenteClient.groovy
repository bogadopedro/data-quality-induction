package com.induction.rest.services

import com.induction.Question
import org.apache.log4j.Logger

/**
 * Created by pbogado on 7/4/17.
 */
class RESTCuriosaMenteClient implements CuriosaMenteClient{

    def rest
    def log = Logger.getLogger(this.class)
    def grailsApplication

    @Override
    def getAllQuestions() {

        log.info("Getting questions from server")

        String endpoint = grailsApplication.config.curiosamente.url

        String uri = endpoint + "/question"

        Question[] response = rest.getRestTemplate().getForObject(uri, Question[].class)

        response.each {
            log.debug(it)
        }

        return response

    }

}
