package com.moises.kotlinroom.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by Moises on 7/25/2019.
 */
@Database(entities = arrayOf(Expenses::class), version = 1, exportSchema = false)
abstract class KotlinRoomDatabase : RoomDatabase() {

    abstract fun kotlinRoomDao(): KotlinRoomDao

    companion object {

        private val DB_NAME = "RoomDatabase.db"
        @Volatile
        private var instance: KotlinRoomDatabase? = null

        fun getInstance(context: Context?): KotlinRoomDatabase? {
            if (instance == null) {
                synchronized(KotlinRoomDatabase::class) {
                    instance = Room.databaseBuilder(context!!.applicationContext,
                        KotlinRoomDatabase::class.java, DB_NAME)
                        .build()
                }
            }
            return instance
        }
    }

}