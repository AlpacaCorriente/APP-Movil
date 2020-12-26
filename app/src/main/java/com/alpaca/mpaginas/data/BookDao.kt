package com.alpaca.mpaginas.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alpaca.mpaginas.model.Book

@Dao
interface BookDao{

    @Query("SELECT * FROM book_table")
    fun getAllData(): LiveData<List<Book>>

    @Query("SELECT * FROM book_table WHERE id= :id")
    fun getById(id: Int ): Book

    @Update
    suspend  fun updateBook(book: Book)

    @Insert
    suspend  fun addBook( book: Book)

    @Delete
    fun delete( person: Book)
}