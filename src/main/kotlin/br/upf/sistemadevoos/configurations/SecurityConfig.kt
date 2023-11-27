package br.upf.sistemadevoos.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebSecurity
@CrossOrigin("*")
class SecurityConfig(val securityFilter: LoginChecker) {

    @Configuration
    class CorsConfiguration : WebMvcConfigurer {
        override fun addCorsMappings(registry: CorsRegistry) {
            registry.addMapping("/*")
                    .allowedOrigins("")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
        }
    }

    @Bean
    fun securityFilterChain(
            httpSecurity: HttpSecurity
    ): SecurityFilterChain {
        return httpSecurity
                .csrf { it.disable() }.cors { }
                .sessionManagement {
                    it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                }
                .authorizeHttpRequests {
                    it.requestMatchers(HttpMethod.GET, "/voos", "/usuarios", "/ticket/*").permitAll()
                            .requestMatchers(HttpMethod.GET, "/voos/*", "/usuarios/*").permitAll()
                            .requestMatchers(HttpMethod.GET, "/cidades", "cidades/*").permitAll()
                            .requestMatchers(HttpMethod.POST, "/cidades", "cidades/*" ).permitAll()
                            .requestMatchers(HttpMethod.POST,"/auth/*").permitAll()
                            .requestMatchers(HttpMethod.POST, "/ticket").permitAll()
                            .requestMatchers(HttpMethod.POST, "/voos", "/usuarios").permitAll()
                            .requestMatchers(HttpMethod.POST, "/voos", "/usuarios", "/comprar").hasRole("USER")
                            .requestMatchers(HttpMethod.POST, "/voos", "/usuarios", "/cancelar").hasRole("USER")
                            .requestMatchers(HttpMethod.PUT, "/voos/*", "/usuarios/*").permitAll()
                            .requestMatchers(HttpMethod.DELETE, "/voos/*", "/usuarios/*", "/ticket/*", "/cidades/*").permitAll()
                            .requestMatchers(HttpMethod.GET, "/ticket").permitAll()
                            .requestMatchers(HttpMethod.GET, "/usuarios").permitAll()
                            .anyRequest().authenticated()
                }
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter::class.java)
                .build()
    }

    @Bean
    fun authenticationManager(
            authenticationConfiguration: AuthenticationConfiguration
    ): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}