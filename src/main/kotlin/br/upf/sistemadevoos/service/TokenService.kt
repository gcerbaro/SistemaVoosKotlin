package br.upf.sistemadevoos.service

import br.upf.sistemadevoos.model.Usuario
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class TokenService {
    @Value("\${api.security.token.secret}")
    private lateinit var secret: String

    fun generateToke(usuario: Usuario): String {
        return try {
            val algorithm = Algorithm.HMAC256(secret)
            JWT.create()
                .withIssuer("auth-api")
                .withSubject(usuario.email)
                .withExpiresAt(genExpirationDate())
                .sign(algorithm)
        } catch (expection: JWTCreationException) {
            throw RuntimeException("Erro na geracao de token!", expection)
        }
    }

    fun validateToken (token: String): String{
        return try {
            val algorithm = Algorithm.HMAC256(secret)
            JWT.require(algorithm)
                .withIssuer("auth-api")
                .build()
                .verify(token)
                .subject
        } catch (expection: JWTVerificationException) {
            ""
        }
    }

    private fun genExpirationDate() =
        LocalDateTime.now().plusHours(2)
            .toInstant(ZoneOffset.of("-03:00"))

}