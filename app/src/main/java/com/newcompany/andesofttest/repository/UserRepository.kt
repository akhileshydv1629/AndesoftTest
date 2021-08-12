package com.newcompany.roomdatabasetask.repository

import com.newcompany.andesofttest.model.User
import com.newcompany.roomdatabasetask.db.UserDAO

class UserRepository(private val dao: UserDAO) {
    suspend fun insertUser(user: User)= dao.insertUser(user)
    fun getAllUsers()= dao.getUser()
    }