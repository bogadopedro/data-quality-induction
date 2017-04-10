package com.induction

import grails.converters.JSON

import javax.servlet.http.HttpServletResponse

class CheckSpellerController {

    def checkSpellerService

    def checkSentence(String sentence) {

        int error

        String jsonReq = request.getJSON()["sentence"]

        if (jsonReq) {
            error = checkSpellerService.checkSentence(jsonReq)
        } else {
            return [status: HttpServletResponse.SC_BAD_REQUEST]
        }

        render {["error": error]} as JSON
    }

}
