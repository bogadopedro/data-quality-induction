package com.induction.rest.services

/**
 * Created by pbogado on 7/4/17.
 */
class MockWordReferenceClient implements WordReferenceClient {

    @Override
    boolean wordContainsError(String word) {
        return false
    }

    @Override
    boolean sentenceContainsError(String word) {
        return false
    }
}
