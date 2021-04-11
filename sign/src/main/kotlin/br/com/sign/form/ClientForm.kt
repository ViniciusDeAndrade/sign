package br.com.sign.form

import br.com.sign.model.Client
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
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
    password = encode(password)
)

fun encode(password: String) =
    BCryptPasswordEncoder().encode(
        password
)
