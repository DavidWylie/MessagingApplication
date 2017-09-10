package messaging.subscriber.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.i18n.phonenumbers.PhoneNumberUtil
import messaging.model.RabbitMessage
import spock.lang.Specification

class MessageHandlerImplSpec extends Specification {
    def "HandleMessage"() {
        given: "fully setup service"
        def service = new MessageHandlerImpl()
        def exampledata = PhoneNumberUtil.getInstance().getExampleNumber("GB")
        def phoneNumber = PhoneNumberUtil.getInstance().format(exampledata,PhoneNumberUtil.PhoneNumberFormat.E164)
        def data = new RabbitMessage(UUID.randomUUID().toString(), phoneNumber)
        def dataBytes = new ObjectMapper().writeValueAsBytes(data)

        when: "handle a message"
        service.handleMessage(dataBytes);

        then: "servie has the data"
        service.data.containsKey("GB")
        service.data.get("GB") == 1
    }

    def "TranslateNumber"() {
        given: "fully setup service"
        def service = new MessageHandlerImpl()
        def exampledata = PhoneNumberUtil.getInstance().getExampleNumber("GB")
        def data = PhoneNumberUtil.getInstance().format(exampledata,PhoneNumberUtil.PhoneNumberFormat.E164)

        when: "given a phone number"
        def result = service.translateNumber(data)

        then: "given region is GB"
        result.getCountryCode() == PhoneNumberUtil.getInstance().getCountryCodeForRegion("GB")
    }

    def "TranslateMessage"() {
        given: "fully setup service"
        def service = new MessageHandlerImpl()
        def data = new RabbitMessage("abc", PhoneNumberUtil.getInstance().getExampleNumber("GB").toString())
        def dataBytes = new ObjectMapper().writeValueAsBytes(data)

        when: "handle a message"
        def result = service.translateMessage(dataBytes)

        then: "servie has the data"
        result == data
    }
}
