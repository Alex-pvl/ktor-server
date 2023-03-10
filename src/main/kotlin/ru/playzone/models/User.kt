package ru.playzone.models

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.playzone.dto.UserDTO

object User: Table("users") {
    private val login = User.varchar("login", 25)
    private val password = User.varchar("password", 25)
    private val username = User.varchar("username", 30)
    private val email = User.varchar("email", 50)

    fun create(userDTO: UserDTO) = transaction {
        User.insert {
            it[login] = userDTO.login
            it[password] = userDTO.password
            it[username] = userDTO.username
            it[email] = userDTO.email
        }
    }

    fun getAll(): List<UserDTO> {
        return try {
            transaction {
                User.selectAll()
                    .map {
                        UserDTO(
                            login = it[login],
                            password = it[password],
                            username = it[username],
                            email = it[email],
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun getOneByLogin(inputLogin: String): UserDTO? {
        return try {
            transaction {
                val user = User.select { login.eq(inputLogin) }.singleOrNull()

                user?.let {
                    UserDTO(
                        login = user[login],
                        password = user[password],
                        username = user[username],
                        email = user[email],
                    )
                }
            }
        } catch (e: Exception) {
            null
        }
    }
}