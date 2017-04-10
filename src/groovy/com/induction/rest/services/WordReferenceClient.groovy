package com.induction.rest.services

/**
 * Created by pbogado on 7/4/17.
 */
interface WordReferenceClient {

    boolean wordContainsError(String word)
    boolean sentenceContainsError(String word)

}