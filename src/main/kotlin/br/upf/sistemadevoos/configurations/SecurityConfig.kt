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

@Configuration
@EnableWebSecurity
class SecurityConfig(val securityFilter: LoginChecker) {

    @Bean
    fun securityFilterChain(
        httpSecurity: HttpSecurity
    ): SecurityFilterChain {
        return httpSecurity
            .csrf { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests {
                it.requestMatchers(HttpMethod.GET, "/voos", "/usuarios", "/ticket/*").permitAll() // Acesso publico a lista
                    .requestMatchers(HttpMethod.GET, "/voos/*", "/usuarios/*").permitAll() // Acesso publico a item
                    .requestMatchers(HttpMethod.GET, "/cidades", "cidades/*").permitAll() // Acesso publico as cidades
                    .requestMatchers(HttpMethod.POST, "/cidades", "cidades/*" ).permitAll() //hasRole("ADMIN") //Apenas admin pode criar cidades
                    .requestMatchers(HttpMethod.POST,"/auth/*").permitAll()
                    .requestMatchers(HttpMethod.POST, "/ticket").permitAll()
                    .requestMatchers(HttpMethod.POST, "/voos", "/usuarios").permitAll() //hasRole("ADMIN") // Apenas admin pode criar voos e usuarios
                    .requestMatchers(HttpMethod.POST, "/voos", "/usuarios", "/comprar").hasRole("USER") // Acesso publico a compra de tickets
                    .requestMatchers(HttpMethod.POST, "/voos", "/usuarios", "/cancelar").hasRole("USER") // Acesso publico ao cancelamento de tickets
                    .requestMatchers(HttpMethod.PUT, "/voos/*", "/usuarios/*").permitAll() //hasRole("ADMIN") // Apenas admin pode atualizar informacoes
                    .requestMatchers(HttpMethod.DELETE, "/voos/*", "/usuarios/*", "/ticket/*", "/cidades/*").permitAll() //hasRole("ADMIN") // Apenas admin pode deletar
                    .requestMatchers(HttpMethod.GET, "/ticket").permitAll() //hasRole("ADMIN") // Apenas admin tem acesso a lista de ticket
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