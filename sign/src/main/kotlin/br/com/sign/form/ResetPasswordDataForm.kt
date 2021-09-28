package br.com.sign.form

data class ResetPasswordDataForm (
    val password: String,
    val repeatedPassword: String,
    val token: String
)
