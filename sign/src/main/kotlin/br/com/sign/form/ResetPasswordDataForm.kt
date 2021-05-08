package br.com.sign.form

data class ResetPasswordDataForm (
    val email: String,
    val token: String,
    val password: String,
    val repeatedPassword: String
)
