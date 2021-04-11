package br.com.sign.config.security

import br.com.sign.exceptions.ApplicationException
import br.com.sign.repository.ClientRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import kotlin.Throws

@Service
class AuthService(
    private val clientRepository: ClientRepository
): UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        val clientOp = this.clientRepository.findByEmail(email)
        if(clientOp.isPresent) return clientOp.get()
        throw ApplicationException("client.not.found")
    }
}