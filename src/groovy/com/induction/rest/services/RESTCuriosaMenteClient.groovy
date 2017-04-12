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
    Question[] getAllQuestions() {

        log.info("Getting questions from CuriosaMente")

        String uri = grailsApplication.config.curiosamente.url + "/question"

        return rest.getRestTemplate().getForObject(uri, Question[].class)

    }

}
