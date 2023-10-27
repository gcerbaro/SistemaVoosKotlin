package br.upf.sistemadevoos.model

import br.upf.sistemadevoos.enums.UserRole
import br.upf.sistemadevoos.enums.UserTitle
import jakarta.persistence.*
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
data class Usuario(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val nome: String,
        val senha: String,
        @Enumerated(value = EnumType.STRING)
        val role: UserRole = UserRole.USER,
        @Enumerated(value = EnumType.STRING)
        val title: UserTitle = UserTitle.Mr,
        val cidade: String,
        val email: String
) : UserDetails {
    override fun getAuthorities(): MutableList<SimpleGrantedAuthority> =
        if (role == UserRole.ADMIN) mutableListOf(
            SimpleGrantedAuthority("ROLE_ADMIN"),
            SimpleGrantedAuthority("ROLE_USER")
        )
        else mutableListOf(SimpleGrantedAuthority("ROLE_USER"))


    override fun getPassword() = senha

    override fun getUsername()= email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}
