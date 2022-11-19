package com.frank.springbootkotlin.service

import com.frank.springbootkotlin.dao.UserDao
import com.frank.springbootkotlin.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired private val userDao: UserDao) {

    fun createUserTable() = userDao.createTable()

    fun createUser(user: User): User = userDao.createUser(user)

}