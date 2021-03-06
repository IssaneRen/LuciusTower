package com.intsig.roomdb.util

import android.content.Context
import androidx.room.Room
import com.intsig.roomdb.LuciusTowerDatabase

/**
 * @author Lucius Ren
 * @since 2021/9/1 22:04
 *
 * 使用单例模式访问数据库的util
 */
object LuciusTowerDatabaseUtil {
    const val DATABASE_NAME_LUCIUS_TOWER = "lucius-tower"

    lateinit var db: LuciusTowerDatabase

    fun initDatabase(context: Context) {
        db = Room.databaseBuilder(
            context, LuciusTowerDatabase::class.java, DATABASE_NAME_LUCIUS_TOWER
        ).allowMainThreadQueries().build()
    }
}