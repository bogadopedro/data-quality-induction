package com.induction.rest.services

import com.induction.Word
import org.apache.log4j.Logger

/**
 * Created by pbogado on 7/4/17.
 */
class RESTWordReferenceClient implements WordReferenceClient {

    def rest
    def log = Logger.getLogger(this.class)
    def grailsApplication


    @Override
    boolean wordContainsError(String word) {

        log.info("Checking word on WordReference")

        log.info("Word:" + word)

        String url = grailsApplication.config.wordreference.url

        String uri = "${url}/definicion/${word}"

        String page = rest.getRestTemplate().getForEntity(uri, String.class)

        if (page.contains("Ningún título tiene")) {
            print word
            print "Palabra con error"
            return true
        } else {
            print "palabra escrita correctamente"
        }

        return false
    }

    @Override
    boolean sentenceContainsError(String sentence) {

        log.info("Checking sentence on WordReference")

        log.info("Word:" + sentence)

        String url = grailsApplication.config.wordreference.url

        String[] words = sentence.replaceAll("[\\P{L}+]", " ").trim().split("\\P{L}+")

        words.each {
            String uri = "${url}/definicion/${it}"

            String page = rest.getRestTemplate().getForEntity(uri, String.class)

            if (page.contains("Ningún título tiene") && !isWordOnDB(it)) {
                print it
                print "Palabra con error"
                return true
            } else {
                print "palabra escrita correctamente"
            }
        }

        return false
    }

    static boolean isWordOnDB(String word) {
//
        Word.saveAll(new Word(word))

        String aux = Word.findByWord(word)

        return aux != null

    }
}
