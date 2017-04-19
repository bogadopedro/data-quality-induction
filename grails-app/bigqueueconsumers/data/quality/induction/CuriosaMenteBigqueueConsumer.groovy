package data.quality.induction


import org.apache.log4j.Logger
import com.mercadolibre.bigqueue.BigQueueConsumer

class CuriosaMenteBigqueueConsumer extends BigQueueConsumer {
    static final Logger log = Logger.getLogger(this)

    def mailSenderService
    String topicName = 'test_topic'
    String consumerName = 'test_consumer'
    Integer concurrency = 1

    void onDelivery(message) {

        try {
            log.info("Message received. ${message}")

            mailSenderService.sendMail(message['msg'])

        } catch(Exception e) {
            log.error("Exception calling send mail", e)
        }
    }
}
