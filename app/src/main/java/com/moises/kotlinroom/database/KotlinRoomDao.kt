package com.moises.kotlinroom.database

import android.arch.persistence.room.*

/**
 * Created by Moises on 7/25/2019.
 */
@Dao
interface KotlinRoomDao {

    @get:Query("SELECT * FROM expenses")
    val allExpenses: List<Expenses>

    @Insert
    fun insert(vararg ip: Expenses): LongArray

    @Update
    fun update(vararg ip: Expenses) :Int

    @Delete
    fun delete(vararg ip: Expenses) : Int



}
