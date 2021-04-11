package br.com.sign.exceptions

import br.com.sign.exceptions.dto.ErroDTO
import br.com.sign.exceptions.dto.FormErrosDTO
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerAdvice(
        private val messageSource: MessageSource
) {

    @ExceptionHandler(ApplicationException::class)
    fun handle(ex: ApplicationException) =
            ResponseEntity.badRequest().body(
                    ErroDTO(ex.message ?: "server.error")
            ).also {
                print(ex) //falta logar
            }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun formErrorHandler(ex: MethodArgumentNotValidException) =
        ex.bindingResult.fieldErrors.map {
            FormErrosDTO(it.field, messageSource.getMessage(it, LocaleContextHolder.getLocale()))
        }
}