package br.com.sign.config.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class EmailConfig {
    @Value("\${spring.mail.host}")
    private val host = ""
    @Value("\${spring.mail.port}")
    private val port: Int = 8080
    @Value("\${spring.mail.username}")
    private val username = ""
    @Value("\${spring.mail.password}")
    private val password = ""
}