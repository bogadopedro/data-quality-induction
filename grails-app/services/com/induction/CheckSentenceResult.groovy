package com.induction

/**
 * Created by pbogado on 11/4/17.
 */
class CheckSentenceResult {

    String idQuestion
    int quantityOfError
    Set errorWords = new HashSet()

    void incrementError(){
        quantityOfError++
    }

}
