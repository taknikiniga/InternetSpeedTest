package com.taknikiniga.internetspeedtest.localdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.taknikiniga.internetspeedtest.localdatabase.model.InternetHistoryModel

@Database(version = 1, entities = [ InternetHistoryModel::class])
@TypeConverters(InternetHistoryModel.Companion.DateTypeConverter::class)
abstract class LocalDatabase :RoomDatabase()  {

    abstract fun localDao():LocalDbDao
}