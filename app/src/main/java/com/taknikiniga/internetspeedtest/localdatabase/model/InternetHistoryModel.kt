package com.taknikiniga.internetspeedtest.localdatabase.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.*

@Entity(tableName = "internet_history")
data class InternetHistoryModel(
    @PrimaryKey(autoGenerate = false)
    val date: Date,
    val download: String="",
    val uploadSpeed: String="",
    val ping: String="",
){
    companion object{
        class DateTypeConverter {
            @TypeConverter
            fun fromDateToLong(date:Date):Long{
                return date.time
            }

            @TypeConverter
            fun fromLongToDate(long:Long):Date{
                return Date(long)
            }
        }
    }
}
