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
    /**
     * 获取当前数据库中的全部人员列表
     */
    @Query("SELECT * FROM person")
    fun getAll(): List<Person?>

    /**
     * 按照 id列表查询用户
     */
    @Query("SELECT * FROM person WHERE uidNum IN (:userIds)")
    fun loadAllByIds(userIds: LongArray): List<Person?>

    /**
     * 根据名字查询person， 模糊查询， 昵称和姓名都可以查
     */
    @Query(
        "SELECT * FROM person WHERE name LIKE :name OR nick_name LIKE :name LIMIT 1"
    )
    fun findByName(name: String): List<Person?>

    @Insert
    fun insertAll(vararg users: Person)

    @Delete
    fun delete(user: Person)
}
