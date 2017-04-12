package com.induction

import grails.transaction.Transactional

@Transactional
class CheckSpellerService {

    def curiosamenteClient

    def wordReferenceClient

    def jedis

    CheckSentenceResult checkSentence(String sentence) {

        CheckSentenceResult checkSentenceResult = new CheckSentenceResult()

        String[] words = sentence.replaceAll("[\\P{L}+]", " ").trim().split("\\P{L}+")

        words.each {

            if (!jedis.exists(it)) {

                def fromDB
                try {
                    fromDB = Word.findByWord(it)
                } catch (Exception e){
                    print "Exception retrieving from DB"
                }

                if (fromDB == null) {

                    boolean error = wordReferenceClient.wordContainsError(it)

                    if (error) {
                        checkSentenceResult.errorWords.add(it)
                        checkSentenceResult.incrementError()
                    } else {
                        print "adding: ${it} : to cache "
                        jedis.set(it, it)
                    }
                } else {
                    print "${it}: was found in DB"
                }
            } else {
                print "${it}: was found in cache"
            }

        }
        jedis.close()

        return checkSentenceResult
    }

    List<CheckSentenceResult> checkQuestions() {

        List<CheckSentenceResult> checkSentenceResults = new ArrayList<>()
        Question[] questionList = curiosamenteClient.getAllQuestions()

        if (questionList) {

            questionList.each {
                log.debug("Sentence to check: ${it}")

                CheckSentenceResult errorResult = checkSentence(it.question)
                errorResult.idQuestion = it.idQuestion
                checkSentenceResults.add(errorResult)
                if (errorResult.quantityOfError != 0) {
                    print "Publish result"
                }
            }
        }
        return checkSentenceResults
    }

}
