package br.com.sign.config.security

import br.com.sign.repository.ClientRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val authService: AuthService,
    private val tokenService: TokenService,
    private val clientRepository: ClientRepository
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
            .antMatchers("/auth/**").permitAll()
            .antMatchers("/password/**").permitAll()
            .anyRequest().authenticated()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilterBefore(
                        AuthTokenFilter(
                            clientRepository = clientRepository,
                            tokenService = tokenService
                ), UsernamePasswordAuthenticationFilter::class.java)

    }

    /**
     *  static resources configuration (js, css ...)
     */
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**")
    }

    @Bean
    override fun authenticationManager(): AuthenticationManager = super.authenticationManager()

}