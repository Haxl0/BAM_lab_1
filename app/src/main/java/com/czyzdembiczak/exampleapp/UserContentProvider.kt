package com.czyzdembiczak.exampleapp

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri


class UserContentProvider : ContentProvider() {
    private var db: SQLiteDatabase? = null

    companion object {
        var uriMatcher: UriMatcher? = null
        private const val PROVIDER_NAME = "com.czyzdembiczak.exampleapp.provider"
        const val DATABASE_NAME = "user_database"
        const val TABLE_NAME = "users"
        const val DATABASE_VERSION = 1
        const val CREATE_DB_TABLE = (" CREATE TABLE " + TABLE_NAME
                + " (uid INTEGER PRIMARY KEY AUTOINCREMENT, "
                + " name TEXT NOT NULL, "
                + " number INTEGER NOT NULL);")

        const val id = "uid"
        const val uriCode = 1
        private val values: HashMap<String, String>? = null

        init {
            uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
            uriMatcher!!.addURI(PROVIDER_NAME, "users", uriCode)
            uriMatcher!!.addURI(PROVIDER_NAME, "users/*", uriCode)
        }
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher!!.match(uri)) {
            uriCode -> "vnd.android.cursor.dir/users"
            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }

    override fun onCreate(): Boolean {
        val context: Context? = context
        val dbHelper = DatabaseHelper(context)
        db = dbHelper.writableDatabase
        return db != null
    }

    override fun query(
        uri: Uri, projection: Array<String?>?, selection: String?,
        selectionArgs: Array<String?>?, sortOrder: String?
    ): Cursor? {
        var sortOrder = sortOrder
        val qb = SQLiteQueryBuilder()
        qb.tables = TABLE_NAME
        when (uriMatcher!!.match(uri)) {
            uriCode -> qb.projectionMap = values
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        if (sortOrder == null || sortOrder === "") {
            sortOrder = id
        }
        val c = qb.query(
            db, projection, selection, selectionArgs, null,
            null, sortOrder
        )
        c.setNotificationUri(context!!.contentResolver, uri)
        return c
    }

    private class DatabaseHelper(context: Context?) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        override fun onCreate(db: SQLiteDatabase) {
//            db.execSQL(CREATE_DB_TABLE)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//            db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
//            onCreate(db)
        }
    }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }
}