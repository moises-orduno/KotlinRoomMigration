package com.moises.kotlinroom.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Moises on 7/25/2019.
 */

class Repository(private val mDao: KotlinRoomDao) {

    suspend fun getAllExpensesEntries(): MutableList<Expenses> {
        return withContext(Dispatchers.Default) {
            mDao.allExpenses.toMutableList()
        }
    }

    suspend fun insertExpensesEntry(entry: Expenses): Long {
        return withContext(Dispatchers.Default) {
            mDao.insert(entry)[0]
        }
    }

    suspend fun updateExpensesEntry(entry: Expenses):Int {
        return withContext(Dispatchers.Default) {
            mDao.update(entry)
        }
    }

    suspend fun deleteExpensesEntry(entry: Expenses):Int {
        return withContext(Dispatchers.Default) {
            mDao.delete(entry)
        }
    }

}
