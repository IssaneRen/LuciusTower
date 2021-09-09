package com.intsig.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Lucius Ren
 * @since 2021/9/1 21:33
 *
 * 数据库实体类  - person 保存每个人的基础信息
 */
@Entity(tableName = "person")
data class Person(
    @PrimaryKey val uidNum: Long,
    @ColumnInfo(name = "full_name") val fullName: String?,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    @ColumnInfo(name = "sex") val sex: Int?,  // 0 for unknown, 1 for male, 2 for female
)
