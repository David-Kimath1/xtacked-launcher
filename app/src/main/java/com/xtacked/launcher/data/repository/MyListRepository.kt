package com.xtacked.launcher.data.repository

import com.xtacked.launcher.data.local.MyListDao
import com.xtacked.launcher.data.local.MyListEntity
import com.xtacked.launcher.data.model.ContentItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Persistent My List backed by Room. Survives process restarts and works offline.
 */
class MyListRepository(private val dao: MyListDao) {

    fun observeItems(): Flow<List<ContentItem>> =
        dao.observeAll().map { list -> list.map { it.toContentItem() } }

    fun observeIds(): Flow<Set<String>> =
        dao.observeIds().map { it.toSet() }

    fun observeIsSaved(id: String): Flow<Boolean> =
        dao.observeIsSaved(id)

    suspend fun isSaved(id: String): Boolean = dao.isSaved(id)

    /** Returns true if the item is saved after this call. */
    suspend fun toggle(item: ContentItem): Boolean {
        return if (dao.isSaved(item.id)) {
            dao.deleteById(item.id)
            false
        } else {
            dao.insert(MyListEntity.from(item))
            true
        }
    }

    suspend fun add(item: ContentItem) {
        dao.insert(MyListEntity.from(item))
    }

    suspend fun remove(id: String) {
        dao.deleteById(id)
    }

    suspend fun clearAll() {
        dao.clear()
    }
}
