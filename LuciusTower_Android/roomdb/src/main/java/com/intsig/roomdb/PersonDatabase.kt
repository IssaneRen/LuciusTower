package com.intsig.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.intsig.roomdb.dao.PersonDao
import com.intsig.roomdb.entity.Person

/**
 * @author Lucius Ren
 * @since 2021/9/1 21:33
 *
 * app使用的 数据库类
 */
@Database(entities = arrayOf(Person::class), version = 1)
abstract class PersonDatabase: RoomDatabase() {
    abstract fun personDao(): PersonDao
}