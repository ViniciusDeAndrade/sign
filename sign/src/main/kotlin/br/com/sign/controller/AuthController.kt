package br.com.sign.controller

import br.com.sign.config.security.TokenService
import br.com.sign.dto.TokenDTO
import br.com.sign.form.LoginForm
import br.com.sign.form.convert
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthController (
    private val authManager: AuthenticationManager,
    private val tokenService: TokenService
){

    @PostMapping
    fun auth(@RequestBody @Valid form: LoginForm) = try {
            val authentication = this.authManager.authenticate(form.convert())
            ResponseEntity.ok(
                TokenDTO(
                    token = tokenService.createToken(authentication),
                    type = "Bearer"
                )
            )
        }catch (ex: AuthenticationException){
             ResponseEntity.badRequest()
        }


}