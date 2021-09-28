package br.com.sign.form

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class ForgotPasswordForm (
    @field:NotBlank
    @field:Email
    val email: String = ""
)
