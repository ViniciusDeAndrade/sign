package br.com.sign.service

import br.com.sign.exceptions.ApplicationException
import br.com.sign.model.Client
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.stereotype.Service
import java.util.*

@Service
class ClientRecoveryService(
   private val clientService: ClientService,
   private val emailService: EmailService
) {

    fun forgottenPassword(email: String) {
        val clientOp = clientService.getClientByEmail(email = email)
        if(!clientOp.isPresent) throw ApplicationException("Não foi encontrado um usuário com o email $email")
        val client = clientOp.get()

        client.resetToken = UUID.randomUUID().toString()
        clientService.updateClient(client)

        println("logging client = $client")

        sendResetPasswordEmail(client)
    }


    fun updatePassword(password: String, token: String) {

    }


    protected fun sendResetPasswordEmail(client: Client) {
        val passwordResetEmail = SimpleMailMessage()
        passwordResetEmail.setFrom("viniciusclo@gmail.com")
        passwordResetEmail.setTo(client.email)
        passwordResetEmail.setSubject("Reset Password")
        passwordResetEmail.setText("To reset your password, click the link below DOING IT" +
                "/reset?token=${client.resetToken}")
        emailService.sendEmail(passwordResetEmail)
    }
}