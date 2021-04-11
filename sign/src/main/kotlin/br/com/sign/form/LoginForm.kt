package br.com.sign.form

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class LoginForm (
    @field:NotBlank
    @field:Email
    val email: String,
    @field:NotBlank
    val password: String
)
fun LoginForm.convert() = UsernamePasswordAuthenticationToken (email, password)

