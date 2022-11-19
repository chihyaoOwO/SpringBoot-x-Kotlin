package com.frank.springbootkotlin.dao

import com.frank.springbootkotlin.model.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserDao(@Autowired private val jdbcTemplate: JdbcTemplate) {

    private val logger: Logger = LoggerFactory.getLogger(this::class.qualifiedName)

    fun createTable() {
        try {
            var tableSchema = StringBuilder().append("CREATE TABLE `user_table`(\n")
                    .append("  `id` varchar(255) NOT NULL,\n")
                    .append("  `name` varchar(255) NOT NULL,\n")
                    .append("  `age` int default NULL,\n")
                    .append("  primary key(id))").toString()
            jdbcTemplate.execute(tableSchema)
        } catch (exception: Exception) {
            logger.error(exception.message)
        }
    }

    fun createUser(user: User): User {
        try {
            user.id = UUID.randomUUID().toString()
            jdbcTemplate.execute("INSERT INTO user_table(id, name, age) VALUES('${user.id}', '${user.userName}', ${user.age})")
        } catch (exception: Exception) {
            logger.error(exception.message)
        }
        return user
    }
}