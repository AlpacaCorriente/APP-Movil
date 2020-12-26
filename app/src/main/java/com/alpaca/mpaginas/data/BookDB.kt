package com.alpaca.mpaginas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alpaca.mpaginas.model.Book

// Dao
@Database(
    entities = [Book::class],
    version = 1,
    exportSchema = false
)
abstract  class BookDB: RoomDatabase(){

    abstract  fun bookDao():BookDao


    companion object {

        @Volatile
        private var INSTANCE: BookDB? = null


        fun getDatabase(contex: Context): BookDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    contex.applicationContext,
                    BookDB::class.java,
                    "book_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}