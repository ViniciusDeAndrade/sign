package br.com.sign.repository

import br.com.sign.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ClientRepository: JpaRepository<Client, Long> {
    fun findByEmail(email: String): Optional<Client>

    fun findByResetToken(resetToken: String): Client?
}
