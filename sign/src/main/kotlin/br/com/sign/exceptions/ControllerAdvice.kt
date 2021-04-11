package br.com.sign.exceptions

import br.com.sign.exceptions.dto.FormErrosDTO
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerAdvice(
        private val messageSource: MessageSource
) {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun formErrorHandler(ex: MethodArgumentNotValidException) =
        ex.bindingResult.fieldErrors.map {
            FormErrosDTO(it.field, messageSource.getMessage(it, LocaleContextHolder.getLocale()))
        }
}