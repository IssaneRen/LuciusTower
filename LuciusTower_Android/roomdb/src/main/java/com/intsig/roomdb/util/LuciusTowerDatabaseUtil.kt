package com.intsig.roomdb.util

import androidx.room.Room
import com.intsig.commonbase.AppLifecycleUtil
import com.intsig.roomdb.LuciusTowerRoomDatabase

/**
 * @author Lucius Ren
 * @since 2021/9/1 22:04
 *
 * 使用单例模式访问数据库的util
 */
object LuciusTowerDatabaseUtil {
    private const val DATABASE_NAME_LUCIUS_TOWER = "lucius-tower"

    var db: LuciusTowerRoomDatabase? = null

    init {
        AppLifecycleUtil.sContext?.let {
            db = Room.databaseBuilder(
                it, LuciusTowerRoomDatabase::class.java, DATABASE_NAME_LUCIUS_TOWER
            ).build()
        }
    }
}