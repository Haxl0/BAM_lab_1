package com.czyzdembiczak.exampleapp.db

class UserRepository(
    private val userDao: UserDao
) {
    suspend fun getUser(username: String) = userDao.getUser(username)

    suspend fun count() = userDao.count()

    suspend fun insertUser(user: User) = userDao.insertAll(user)
}