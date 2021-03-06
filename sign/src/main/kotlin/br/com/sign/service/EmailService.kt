package br.com.sign.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class EmailService (
    private val mailSender: JavaMailSender
){

    @Async
    fun sendEmail(email: SimpleMailMessage) {
        mailSender.send(email)
    }
}
