package com.moises.kotlinroom.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Moises on 7/25/2019.
 */
@Entity(tableName = "expenses")
data class Expenses(@field:PrimaryKey(autoGenerate = true)
                   val id: Int, var name: String, var value:Double,var timestamp:Long)

