package com.frank.springbootkotlin.service

import arrow.core.Either
import com.frank.springbootkotlin.dao.UserDao
import com.frank.springbootkotlin.exception.AppException
import com.frank.springbootkotlin.model.User
import com.frank.springbootkotlin.model.UserRequestVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired private val userDao: UserDao) {

    suspend fun createUserTable() = userDao.createTable()

    suspend fun createUser(userVO: UserRequestVO): Either<AppException, User> = userDao.createUser(userVO)

}