package br.com.sign.form

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class AddressForm(
    @field:NotBlank
    @field:NotNull
    val street: String,

    val number: String = "with no number",

    @field:NotBlank
    @field:NotNull
    val neighborhood: String,

    @field:NotBlank
    @field:NotNull
    val city: String,

    @field:NotBlank
    @field:NotNull
    val state: String
)