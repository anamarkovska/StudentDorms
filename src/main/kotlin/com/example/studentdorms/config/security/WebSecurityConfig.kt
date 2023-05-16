package com.example.studentdorms.config.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*


@Suppress("DEPRECATION")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig(private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
                        private val jwtUserDetailsService: UserDetailsService,
                        private val jwtRequestFilter: JwtRequestFilter,
                        private val passwordEncoder: MyPasswordEncoder
) : WebSecurityConfigurerAdapter() {

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder)
    }

    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {

        httpSecurity.cors(Customizer.withDefaults()).csrf().disable()
            .authorizeRequests().antMatchers("/api/authenticate","/api/register")
            .permitAll().anyRequest()
            .authenticated().and().exceptionHandling()

            .authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://localhost:4200","http://localhost:42631")
        configuration.allowedMethods = listOf(
            "GET", "POST", "PUT", "PATCH",
            "DELETE", "OPTIONS"
        )
        configuration.allowedHeaders = listOf(
            "authorization", "content-type",
            "x-auth-token"
        )
        configuration.exposedHeaders = listOf("x-auth-token")
        configuration.allowCredentials = true
        configuration.maxAge = 3600L
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}