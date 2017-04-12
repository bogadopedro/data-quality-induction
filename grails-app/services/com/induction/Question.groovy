package com.induction

import org.codehaus.jackson.annotate.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown=true)
class Question {

    String idQuestion
    String question

}
