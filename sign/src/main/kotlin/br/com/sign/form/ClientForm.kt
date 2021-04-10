package br.com.sign.form

import br.com.sign.model.Client
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ClientForm(
    @field:NotBlank
    @field:NotNull
    val name: String,
    @field:Email
    @field:NotBlank
    val email: String,
    @field:NotBlank
    val password: String
)
fun ClientForm.toModel() = Client (
    name = name,
    email = email,
    password = hide(password)
)

// TODO() esconder o password
fun hide(password: String) = password
