package br.com.sign.config.security

import br.com.sign.constants.JWT_EXPIRATION
import br.com.sign.constants.JWT_SECRET
import br.com.sign.model.Client
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.time.LocalDate
import java.util.*

@Service
class TokenService {
    fun createToken(authentication: Authentication): String {
        val logged = authentication.principal as Client
        val today = Date()
        val expirationDate = Date(today.time + JWT_EXPIRATION)
        return Jwts.builder()
                .setIssuer("")
                .setSubject(logged.id.toString())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact()
    }

    fun isTokenValid(token: String?): Boolean =
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token)
            true
        }catch (ex: Exception){
            false
        }

    fun getUserId(token: String): Long = Jwts.parser()
            .setSigningKey(JWT_SECRET)
            .parseClaimsJws(token)
            .body.subject as Long




}
