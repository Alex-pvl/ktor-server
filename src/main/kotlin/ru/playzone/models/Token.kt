package ru.playzone.models

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.playzone.dto.TokenDTO

object Token: Table(name = "tokens") {
    private val id = Token.varchar("id", 40)
    private val login = Token.varchar("login", 25)
    private val token = Token.varchar("token", 40)

    fun getAll(): List<TokenDTO> {
        return try {
            transaction {
                Token.selectAll()
                    .map {
                        TokenDTO(
                            id = it[Token.id],
                            token = it[token],
                            login = it[login]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun create(tokenDTO: TokenDTO) = transaction {
        Token.insert {
            it[id] = tokenDTO.id
            it[login] = tokenDTO.login
            it[token] = tokenDTO.token
        }
    }
}