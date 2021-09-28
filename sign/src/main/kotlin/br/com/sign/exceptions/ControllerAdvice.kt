package br.com.sign.exceptions

import br.com.sign.exceptions.dto.ErroDTO
import br.com.sign.exceptions.dto.FormErrosDTO
import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerAdvice(
        private val messageSource: MessageSource
) {

    private val logger = LoggerFactory.getLogger(ControllerAdvice::class.java)

    @ExceptionHandler(ApplicationException::class)
    fun handle(ex: ApplicationException) =
            ResponseEntity.badRequest().body(
                    ErroDTO(ex.message ?: "server.error")
            ).also {
                logger.error("something went wrong", it)
            }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun formErrorHandler(ex: MethodArgumentNotValidException) =
        ex.bindingResult.fieldErrors.map {
            FormErrosDTO(it.field, messageSource.getMessage(it, LocaleContextHolder.getLocale()))
        }.also {
            logger.error("some fields got input validation error", it)
        }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    fun kotlinFormErrorHandler(ex: HttpMessageNotReadableException) =
        ex.message
}