package br.com.sign.form

import br.com.sign.model.Address
import br.com.sign.model.Client
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ClientForm(
    @field:NotBlank
    @field:NotNull
    val name: String,
    @field:NotNull
    @field:Email
    @field:NotBlank
    val email: String,
    val addressForm: AddressForm
)
fun ClientForm.toModel(id: Long, password: String) = Client (
    id = id,
    name = name,
    email = email,
    password = password,
        address = Address(
            street = addressForm.street,
            city = addressForm.city,
            neighborhood = addressForm.neighborhood,
            number = addressForm.number,
            state = addressForm.state
        )
)


