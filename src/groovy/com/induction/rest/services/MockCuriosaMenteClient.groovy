package com.induction.rest.services

import com.induction.Question
import org.apache.log4j.Logger
import org.codehaus.jackson.map.ObjectMapper

import java.nio.file.Paths

/**
 * Created by pbogado on 7/4/17.
 */
class MockCuriosaMenteClient implements CuriosaMenteClient{

    def mockedQuestions
    def log = Logger.getLogger(this.class)

    @Override
    Question[] getAllQuestions() {

        log.info("Mocking questions")

        if(mockedQuestions == null){
            mockedQuestions = "[\n" +
                    "  {\n" +
                    "    \"idQuestion\": \"2\",\n" +
                    "    \"question\": \"¿En qué ciudadd nació el jugador Lionel Messi?\",\n" +
                    "    \"options\": [\n" +
                    "      \"Rosario\",\n" +
                    "      \"Mar del Plata\",\n" +
                    "      \"Santa fe\",\n" +
                    "      \"Bolivar\"\n" +
                    "    ],\n" +
                    "    \"correctAnswer\": \"Rosario\",\n" +
                    "    \"description\": \"Naci´0 el 24 de Junio de 1987 en Rosario. Tiene 29 años\",\n" +
                    "    \"category\": {\n" +
                    "      \"idCategory\": \"57d743f5e4b087673b8c9f7f\",\n" +
                    "      \"name\": \"Deporte\"\n" +
                    "    },\n" +
                    "    \"subcategory\": null,\n" +
                    "    \"currentPosition\": 0\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"idQuestion\": \"4\",\n" +
                    "    \"question\": \"¿En qué año murió Juan Domingo Perón?\",\n" +
                    "    \"options\": [\n" +
                    "      \"1970\",\n" +
                    "      \"1972\",\n" +
                    "      \"1973\",\n" +
                    "      \"1974\"\n" +
                    "    ],\n" +
                    "    \"correctAnswer\": \"1974\",\n" +
                    "    \"description\": \"Falleció el 1 de Julio de 1974 de un para cardíaco en la quinta de Olivos\",\n" +
                    "    \"category\": {\n" +
                    "      \"idCategory\": \"57d743f9e4b087673b8c9f80\",\n" +
                    "      \"name\": \"Historia\"\n" +
                    "    },\n" +
                    "    \"subcategory\": null,\n" +
                    "    \"currentPosition\": 0\n" +
                    "  }]"
        }
        println "*************************************************************"
        println mockedQuestions
        println "*************************************************************"

        return new ObjectMapper().readValue(mockedQuestions, Question[].class)

    }

}
