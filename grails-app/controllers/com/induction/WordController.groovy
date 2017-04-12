package com.induction

import javax.servlet.http.HttpServletResponse

class WordController {

    def addWord() {

        String word = request.getJSON()["word"]

        if (!word) {
            return [response: [error: "Missing param: word"], status: HttpServletResponse.SC_BAD_REQUEST]
        }
        try {
            Word.saveAll(new Word(word))
        } catch (Exception ex){
            return [response: [error: "Error on DB."], status: HttpServletResponse.SC_INTERNAL_SERVER_ERROR]
        }

        return [response: ["${word} Word added successfully."], status: HttpServletResponse.SC_OK]
    }

}
