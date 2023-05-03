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
//@Import(CorsConfig::class)
class WebSecurityConfig(private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
                        private val jwtUserDetailsService: UserDetailsService,
                        private val jwtRequestFilter: JwtRequestFilter,
                        private val passwordEncoder: MyPasswordEncoder
) : WebSecurityConfigurerAdapter() {

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder)
    }

//    @Bean
//    @Throws(Exception::class)
//    override fun authenticationManagerBean(): AuthenticationManager {
//        return super.authenticationManagerBean()
//    }

    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {
        // We don't need CSRF for this example
        httpSecurity.cors(Customizer.withDefaults()).csrf().disable() // dont authenticate this particular request
            .authorizeRequests().antMatchers("/api/authenticate","/api/register")
            .permitAll().anyRequest() // all other requests need to be authenticated
            .authenticated().and().exceptionHandling() // make sure we use stateless session; session won't be used to
            // store user's state.
            .authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
        // allow cross-origin requests
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