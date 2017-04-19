package com.induction.feed
/**
 * Created by pbogado on 18/4/17.
 */
class TypoCheckerProducerImpl implements TypoCheckerProducer {

    String topicName = 'test_topic'

    def grailsApplication


    @Override
    def publishMessage(message) {

        grailsApplication.getMainContext().getBean("bigQueueProducer").send(['test_topic'], message)

    }
}
