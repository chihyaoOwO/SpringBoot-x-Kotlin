package com.frank.springbootkotlin.dao

import arrow.core.Either
import com.frank.springbootkotlin.exception.AppException
import com.frank.springbootkotlin.model.User
import com.frank.springbootkotlin.model.UserRequestVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserDao(@Autowired @Qualifier("h2JdbcTemplate") private val jdbcTemplate: JdbcTemplate) {

    private val logger: Logger = LoggerFactory.getLogger(this::class.qualifiedName)

    suspend fun createTable(): Either<AppException, Unit> =
        Either.catch {
            var tableSchema = StringBuilder().append("CREATE TABLE `user_table`(\n")
                .append("  `id` varchar(255) NOT NULL,\n")
                .append("  `username` varchar(255) NOT NULL,\n")
                .append("  `age` int default NULL,\n")
                .append("  primary key(id))").toString()
            withContext(Dispatchers.IO) {
                jdbcTemplate.execute(tableSchema)
            }
        }.mapLeft {
            AppException.DataBaseError(it)
        }

    suspend fun createUser(userVO: UserRequestVO): Either<AppException, User> =
        Either.catch {
            User(UUID.randomUUID().toString(), userVO.username, userVO.age)
                .apply {
                    withContext(Dispatchers.IO) {
                        jdbcTemplate.update(
                            "INSERT INTO user_table (id, username, age) VALUES (?, ?, ?)",
                            id, username, age
                        )
                    }
                }
        }.mapLeft {
            AppException.DataBaseError(it)
        }

}