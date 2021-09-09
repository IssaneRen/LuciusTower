package com.intsig.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.intsig.roomdb.entity.Person

/**
 * @author Lucius Ren
 * @since 2021/9/1 21:42
 *
 * 对应Person表的Dao接口
 */
@Dao
interface PersonDao {
    @Query("SELECT * FROM person")
    fun getAll(): List<Person>

    @Query("SELECT * FROM person WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: LongArray): List<Person>

    @Query(
        "SELECT * FROM person WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1"
    )
    fun findByName(first: String, last: String): Person

    @Query(
        "SELECT * FROM person WHERE full_name LIKE :fullName  LIMIT 1"
    )
    fun findByFullName(fullName: String): Person

    @Insert
    fun insertAll(vararg users: Person)

    @Delete
    fun delete(user: Person)
}