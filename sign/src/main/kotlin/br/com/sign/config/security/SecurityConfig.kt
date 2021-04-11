package br.com.sign.config.security

import br.com.sign.constants.CLIENT_BASE_PATH
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val authService: AuthService
) : WebSecurityConfigurerAdapter() {

    /**
     * authentication configuration
     */
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(authService)
    }

    /**
     * authorization configuration
     */
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, CLIENT_BASE_PATH).permitAll()
                .anyRequest().authenticated()
                .and().formLogin()

    }

    /**
     *  static resources configuration (js, css ...)
     */
    override fun configure(web: WebSecurity) {

    }

}