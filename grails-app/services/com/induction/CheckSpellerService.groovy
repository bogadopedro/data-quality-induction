package com.induction

import grails.transaction.Transactional

@Transactional
class CheckSpellerService {

    def curiosamenteClient

    def wordReferenceClient

    def checkSentence(String sentence) {

        int quantityError = 0
        String[] words = sentence.replaceAll("[\\P{L}+]", " ").trim().split("\\P{L}+")

        words.each {

            boolean error = wordReferenceClient.wordContainsError(it)

            if (error) {
                quantityError++
            }

        }

        return quantityError
    }

    def checkQuestions() {

        Question[] questionList = curiosamenteClient.getAllQuestions()

        if (questionList) {

            questionList.each {
                if (containsError(it.question)) {
                    publishError(it)
                }
            }
        }
    }

    def publishError(Question question) {
        log.info("Publishing message")
        saveOnCache()
    }

    Object saveOnCache() {
        log.info("Saving on cache")
    }

    boolean containsError(String question) {

        log.debug("Question to check: ${question}")

        boolean sentenceContainsError = wordReferenceClient.sentenceContainsError(question)

        log.debug("result: ${sentenceContainsError}")

        return sentenceContainsError

    }
}
