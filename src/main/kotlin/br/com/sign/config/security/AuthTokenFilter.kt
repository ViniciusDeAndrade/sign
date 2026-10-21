package br.com.sign.config.security

import br.com.sign.repository.ClientRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.jvm.Throws

class AuthTokenFilter(
    private val tokenService: TokenService,
    private val clientRepository: ClientRepository
) : OncePerRequestFilter() {


    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

        val token = getToken(request)
        val valid = tokenService.isTokenValid(token)
        if (valid) authClient(token!!)

        filterChain.doFilter(request, response)
    }

    private fun getToken(request: HttpServletRequest): String? {
        val token = request.getHeader("Authorization")
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) return null
        return token.substring(7, token.length)
    }

    private fun authClient(token: String) {
        val id = tokenService.getUserId(token)
        val user = clientRepository.findById(id).get()
        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(
                user,  null,  user.authorities
        )
    }
}