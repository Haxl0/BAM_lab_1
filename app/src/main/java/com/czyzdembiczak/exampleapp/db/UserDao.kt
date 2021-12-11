package com.czyzdembiczak.exampleapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM users where users.username == :username")
    fun getUser(username: String): User

    @Query("SELECT COUNT(*) FROM users")
    fun count(): Int

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}