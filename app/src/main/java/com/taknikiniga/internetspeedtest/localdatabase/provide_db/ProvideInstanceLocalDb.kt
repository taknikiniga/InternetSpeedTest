package com.taknikiniga.internetspeedtest.localdatabase.provide_db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.taknikiniga.internetspeedtest.localdatabase.LocalDatabase
import com.taknikiniga.internetspeedtest.localdatabase.LocalDbDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideInstanceLocalDb {

    @Provides
    @Singleton
    fun provideLocalDb(@ApplicationContext context: Context): LocalDatabase {
        return Room.databaseBuilder(context, LocalDatabase::class.java, "internet_speed_db").build()
    }

    @Provides
    @Singleton
    fun provideDao(localDatabase: LocalDatabase): LocalDbDao {
        return localDatabase.localDao()
    }

}