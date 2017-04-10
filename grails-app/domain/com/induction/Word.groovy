package com.induction

class Word {

    String word

    Word() {
    }

    Word(word) {
        this.word = word
    }

    static constraints = {
        word blank: false, nullable: false, unique: true
    }

    static mapWith = "mongo"

    static mapping = {
        database "induction"
        collection "words"
    }

}
