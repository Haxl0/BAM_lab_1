package com.czyzdembiczak.exampleapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Long? = null,
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "number") val number: Int?
)
