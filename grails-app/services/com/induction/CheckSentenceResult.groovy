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

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        CheckSentenceResult that = (CheckSentenceResult) o

        if (quantityOfError != that.quantityOfError) return false
        if (errorWords != that.errorWords) return false
        if (idQuestion != that.idQuestion) return false

        return true
    }

}
