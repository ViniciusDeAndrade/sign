package br.com.sign.service

import br.com.sign.dto.ClientDTO
import br.com.sign.exceptions.ApplicationException
import br.com.sign.form.ClientForm
import br.com.sign.form.ResetPasswordDataForm
import br.com.sign.model.Client
import br.com.sign.model.toDTO
import br.com.sign.utils.encode
import org.springframework.mail.SimpleMailMessage
import org.springframework.stereotype.Service
import java.util.*

@Service
class ClientRecoveryService(
   private val clientService: ClientService,
   private val emailService: EmailService
) {

    fun updatePassword(resetPasswordDataForm: ResetPasswordDataForm) {
        if(resetPasswordDataForm.password != resetPasswordDataForm.repeatedPassword) throw ApplicationException("As senhas não conferem")
        val client = clientService.findByResetToken(resetPasswordDataForm.token) ?: throw ApplicationException("client.not.found")
        clientService.updateClient(
            client.copy(
                password = encode(resetPasswordDataForm.password),
                resetToken = ""
            )
        )
    }

    fun forgottenPassword(email: String) {
        val clientOp = clientService.getClientByEmail(email = email)
        val client = clientOp.orElseThrow { ApplicationException("Could not found an user with the email: $email") }

        client.resetToken = UUID.randomUUID().toString()
        clientService.updateClient(client)

        println("logging client = $client")

        sendResetPasswordEmail(client)
    }


    protected fun sendResetPasswordEmail(client: Client) {
        val passwordResetEmail = SimpleMailMessage()
        passwordResetEmail.setFrom("viniciusclo@gmail.com")
        passwordResetEmail.setTo(client.email)
        passwordResetEmail.setSubject("Reset Password")
        passwordResetEmail.setText("To reset your password, click the link below DOING IT " +
                "http://localhost:8080/password/reset?token=${client.resetToken}")
        emailService.sendEmail(passwordResetEmail)
    }

    fun getClientByToken(token: String): ClientDTO =
        clientService.findByResetToken(token)?.toDTO() ?: throw ApplicationException("client.not.found")

}