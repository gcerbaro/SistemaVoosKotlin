package br.upf.sistemadevoos.model

import br.upf.sistemadevoos.enums.UserRole
import jakarta.persistence.*
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class Company(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val nome: String,
        val senha: String,
        val razaoSocial : String,
        val cnpj : String,
        @Enumerated(value = EnumType.STRING)
        val role: UserRole = UserRole.COMPANY,
        val cidade: String,
        val email: String,
) : UserDetails {
        override fun getAuthorities(): MutableList<SimpleGrantedAuthority> =
               mutableListOf(SimpleGrantedAuthority("ROLE_USER"))


        override fun getPassword() = senha

        override fun getUsername()= email

        override fun isAccountNonExpired() = true

        override fun isAccountNonLocked() = true

        override fun isCredentialsNonExpired() = true

        override fun isEnabled() = true
}
