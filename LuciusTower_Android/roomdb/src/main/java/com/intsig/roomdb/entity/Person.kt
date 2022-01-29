package com.intsig.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.intsig.roomdb.util.LTConverter
import java.util.*

/**
 * @author Lucius Ren
 * @since 2021/9/1 21:33
 *
 * 数据库实体类  - person 保存每个人的基础信息
 */
@TypeConverters(LTConverter.LTDateConverter::class)
@Entity(tableName = "person")
data class Person(
    // 区块1 --- 唯一标识
    @PrimaryKey(autoGenerate = true) val uidNum: Long?,

    // 区块2 --- 基本信息
    /**
     * 思来想去，感觉我们的数据仍然没有分开存储姓名的习惯，所以只存名字和昵称
     */
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "nick_name") val nickName: String?,
    @ColumnInfo(name = "sex", defaultValue = "0") val sex: Int,  // 0 for unknown, 1 for male, 2 for female
    /**
     * 备注
     */
    @ColumnInfo(name = "note") val note: String?,
    /**
     * 出生日期
     */
    @ColumnInfo(name = "birth_date") val birthDay: Date?,

    // 区块3 --- 标签（待定）
    // 区块4 ---
)
