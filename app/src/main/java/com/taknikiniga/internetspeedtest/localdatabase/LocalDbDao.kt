package com.taknikiniga.internetspeedtest.localdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.taknikiniga.internetspeedtest.localdatabase.model.InternetHistoryModel
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalDbDao {

    @Insert
    suspend fun insertInternetHistory(internetHistoryModel: InternetHistoryModel)

    @Query("SELECT * FROM internet_history")
    fun getInternetHistory():Flow<List<InternetHistoryModel>>
}