package com.induction.rest.services

import org.apache.log4j.Logger

/**
 * Created by pbogado on 7/4/17.
 */
class RESTWordReferenceClient implements WordReferenceClient {

    public static final String NOT_FOUND = "Ningún título tiene"
    def rest
    def log = Logger.getLogger(this.class)
    def grailsApplication


    @Override
    boolean wordContainsError(String word) {

        log.info("Checking word: ${word} : on WordReference")

        String uri = grailsApplication.config.wordreference.url + "/definicion/${word}"

        String page = rest.getRestTemplate().getForEntity(uri, String.class)

        if (page.contains(NOT_FOUND)) {
            print "${word}: Palabra con error"
            return true
        } else {
            print "${word}: Palabra escrita correctamente"
            return false
        }
    }

}
