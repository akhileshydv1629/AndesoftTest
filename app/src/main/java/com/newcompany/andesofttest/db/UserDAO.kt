package com.newcompany.roomdatabasetask.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.newcompany.andesofttest.model.User

@Dao
interface UserDAO{

    @Insert
    suspend fun insertUser(user: User):Long

    @Query("Select * from user_table")
    fun getUser(): LiveData<List<User>>


}