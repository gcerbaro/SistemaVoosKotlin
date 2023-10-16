package br.upf.sistemadevoos.model

import jakarta.persistence.*

@Entity
data class Usuario(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val nome: String,
    val senha: String,
    @Enumerated(value = EnumType.STRING)
    val role: UserRole = UserRole.USER,
    val cidade: String,
    val email: String,
    @OneToMany
    var tickets: List<Ticket> = listOf()
)
