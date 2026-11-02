package com.xtacked.launcher.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MyListDao {
    @Query("SELECT * FROM my_list ORDER BY addedAt DESC")
    fun observeAll(): Flow<List<MyListEntity>>

    @Query("SELECT id FROM my_list ORDER BY addedAt DESC")
    fun observeIds(): Flow<List<String>>

    @Query("SELECT EXISTS(SELECT 1 FROM my_list WHERE id = :id)")
    fun observeIsSaved(id: String): Flow<Boolean>

    @Query("SELECT EXISTS(SELECT 1 FROM my_list WHERE id = :id)")
    suspend fun isSaved(id: String): Boolean

    @Query("SELECT * FROM my_list WHERE id = :id LIMIT 1")
    suspend fun findById(id: String): MyListEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: MyListEntity)

    @Query("DELETE FROM my_list WHERE id = :id")
    suspend fun deleteById(id: String)
}
