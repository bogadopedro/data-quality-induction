package com.induction

import grails.transaction.Transactional

@Transactional
class MailSenderService {

    def mailService

    boolean sendMail(message) {

        try {

            String textToSent = "Mensajes procesados: ${message}"

            mailService.sendMail {
                multipart true
                to "pedro.bogado@mercadolibre.com"
                subject "Checked questions"
                text textToSent
                html "<html>" +
                        "  <body>" +
                        "    <p>" + textToSent +
                        "</p>" +
                        "    <img src='https://http2.mlstatic.com/ui/navigation/2.0.3/mercadolibre/logo__large.png' />" +
                        "  </body>" +
                        "</html>"
            }
            print 'message sent by email'
        } catch (Exception e) {
            print e
            throw e
        }
        return true
    }
}
