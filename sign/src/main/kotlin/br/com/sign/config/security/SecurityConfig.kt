package br.com.sign.config.security

import br.com.sign.constants.CLIENT_BASE_PATH
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

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
                .passwordEncoder(BCryptPasswordEncoder())
    }

    /**
     * authorization configuration
     */
    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, CLIENT_BASE_PATH).permitAll()
            .antMatchers("/auth").permitAll()
            .anyRequest().authenticated()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

    }

    /**
     *  static resources configuration (js, css ...)
     */
    override fun configure(web: WebSecurity) {

    }

    @Bean
    override fun authenticationManager(): AuthenticationManager = super.authenticationManager()

}