import com.example.studentdorms.config.JwtAuthenticationFilter
import com.example.studentdorms.config.JwtAuthorizationFilter
import com.example.studentdorms.config.JwtTokenUtil
import com.example.studentdorms.config.MyPasswordEncoder
import com.example.studentdorms.service.impl.UserDetailsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = ["com.example.studentdorms"])
class SecurityConfig(
    private val userDetailsService: UserDetailsServiceImpl,
    private val jwtTokenUtil: JwtTokenUtil,
    private val passwordEncoder: MyPasswordEncoder
) : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return passwordEncoder
    }
    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
    }
    @Throws(Exception::class)
    @Autowired
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/auth").permitAll()
            .antMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
            .anyRequest().permitAll()
            .and()
            .addFilterBefore(JwtAuthenticationFilter (authenticationManager(), jwtTokenUtil), UsernamePasswordAuthenticationFilter::class.java)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }
}
