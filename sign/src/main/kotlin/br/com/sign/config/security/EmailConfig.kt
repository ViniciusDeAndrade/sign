package br.com.sign.config.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl

@Configuration
class EmailConfig (
    @Value("\${spring.mail.host}")
    private val host: String = "",
    @Value("\${spring.mail.port}")
    private val port: Int = 2525,
    @Value("\${spring.mail.username}")
    private val username: String = "",
    @Value("\${spring.mail.password}")
    private val password: String = ""
)