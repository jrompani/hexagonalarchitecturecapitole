package com.app.hexarchitecture.capitole.infrastructure.controller

import com.app.hexarchitecture.capitole.application.exceptions.ResourceNotAvailableException
import com.app.hexarchitecture.capitole.application.service.PriceCommandService
import com.app.hexarchitecture.capitole.infrastructure.adapters.in.controller.price.PriceController
import com.app.hexarchitecture.capitole.infrastructure.exception.GlobalExceptionHandler
import com.app.hexarchitecture.capitole.tools.ObjectsConstructor
import org.slf4j.Logger
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import spock.lang.Specification
import spock.lang.Subject
import java.time.LocalDateTime;
import com.app.hexarchitecture.capitole.infrastructure.adapters.in.controller.price.model.Error

class PriceControllerTest extends Specification {
    @Subject
    private PriceCommandService priceCommandService= Mock(PriceCommandService.class);

    private Logger logger = Mock(Logger.class)

    private PriceController priceController = Mock(PriceController.class)

    private ObjectsConstructor objectsConstructor = Mock(ObjectsConstructor);

    def "handlePriceNotFoundException should return the expected response"() {
        given:
        def exception = new ResourceNotAvailableException("Entity not available")
        def controllerAdvice = new GlobalExceptionHandler(logger)

        when:
        def response = controllerAdvice.handlePriceNotFoundException(exception)

        then:
        response != null
        response.success == false
        response.data == null
        response.error != null
        response.error.code == ResourceNotAvailableException.ERROR_CODE
        response.error.error == "ResourceNotAvailableException"
        response.error.cause == "Incorrect parameters or Entity is not available"
    }

    def "handleUncaughtException should return the expected response"() {
        given:
        def exception = new Exception("Some uncaught exception")
        def controllerAdvice = new GlobalExceptionHandler(logger)

        when:
        def response = controllerAdvice.handleUncaughtException(exception)

        then:
        response != null
        response.success == false
        response.data == null
        response.error != null
        response.error.code == "uncaughtException"
        response.error.error == "Exception"
        response.error.cause == "Some uncaught exception"
    }

    def "handleMethodArgumentTypeMismatch should return the expected response"() {
        given:
        def ex = new MethodArgumentTypeMismatchException("name", String.class, "value", null, null)
        def controllerAdvice = new GlobalExceptionHandler(logger)

        when:
        def response = controllerAdvice.handleMethodArgumentTypeMismatch(ex)

        then:
        response != null
        response.success == false
        response.data == null
        response.error != null
        response.error.code == ex.errorCode
        response.error.error == "MethodArgumentTypeMismatchException"
        response.error.cause == "Invalid argument type: value should be String"
    }
}
