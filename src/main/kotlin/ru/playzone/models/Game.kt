package ru.playzone.models

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.playzone.dto.GameDTO

object Game : Table("games") {
    private val gameId = Game.varchar("id", 40)
    private val title = Game.varchar("title", 25)
    private val logo = Game.varchar("logo", 50)
    private val description = Game.varchar("description", 500)
    private val version = Game.varchar("version", 20)
    private val weight = Game.integer("weight")
    private val downloadCount = Game.integer("download_count")

    fun getAll(): List<GameDTO> {
        return try {
            transaction {
                Game.selectAll()
                    .map {
                        GameDTO(
                            gameId = it[gameId],
                            title = it[title],
                            logo = it[logo],
                            description = it[description],
                            version = it[version],
                            weight = it[weight],
                            downloadCount = it[downloadCount],
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun create(gameDTO: GameDTO) = transaction {
        Game.insert {
            it[gameId] = gameDTO.gameId
            it[title] = gameDTO.title
            it[logo] = gameDTO.logo
            it[description] = gameDTO.description
            it[version] = gameDTO.version
            it[weight] = gameDTO.weight
            it[downloadCount] = gameDTO.downloadCount
        }
    }
}