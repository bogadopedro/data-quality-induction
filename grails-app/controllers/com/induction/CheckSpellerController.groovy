package com.induction

import com.ml.exceptions.MethodNotAllowedException

import javax.servlet.http.HttpServletResponse

class CheckSpellerController {

    def checkSpellerService

    def checkSentence() {

        CheckSentenceResult errorResult

        String jsonReq = request.getJSON()["sentence"]

        if (jsonReq) {
            errorResult = checkSpellerService.checkSentence(jsonReq)
        } else {
            return [response: [error: "Missing param: sentence"], status: HttpServletResponse.SC_BAD_REQUEST]
        }

        return [response: errorResult, status: HttpServletResponse.SC_OK]

    }

    def checkQuestions() {
        //TODO - thread
        checkSpellerService.checkQuestions()
        return [response: "Process has been started", status: HttpServletResponse.SC_OK]
    }

    def methodNotAllowed = {
        throw new MethodNotAllowedException("Method not allowed")
    }

}
