package com.czyzdembiczak.exampleapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "users")
data class User(
    @PrimaryKey val uid: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "number") val number: Int?
)
