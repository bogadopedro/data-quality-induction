package com.induction.rest.services

/**
 * Created by pbogado on 7/4/17.
 */
class MockWordReferenceClient implements WordReferenceClient {


    public static final String MOCKS_WORDS = "hola"

    @Override
    boolean wordContainsError(String word) {
        return !MOCKS_WORDS.contains(word)
    }

}
