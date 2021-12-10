package com.intsig.roomdb.util

import androidx.room.TypeConverter
import java.util.*

/**
 * @author Lucius Ren
 * @since 2021/12/9 20:10
 *
 * LuciusTower 会使用的全部converter
 */
class LTConverter {
    class LTDateConverter {
        @TypeConverter
        fun getDateFromString(value: Long): Date {
            return Date(value)
        }

        @TypeConverter
        fun getStringFromDate(value: Date): Long {
            return value.time
        }
    }
}