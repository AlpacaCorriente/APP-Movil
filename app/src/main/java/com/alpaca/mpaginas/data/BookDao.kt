package com.alpaca.mpaginas.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alpaca.mpaginas.model.Book

@Dao
interface BookDao{

    @Query("SELECT * FROM book_table")
    fun getAllData(): LiveData<List<Book>>
    @Query("SELECT * FROM book_table WHERE state=0")
    fun getInLibraryBook():LiveData<List<Book>>

    @Query("SELECT * FROM book_table WHERE state=1")
    fun getReadingBook():LiveData<List<Book>>

    @Query("SELECT * FROM book_table WHERE state=2")
    fun getReadsBook():LiveData<List<Book>>


    @Query("SELECT * FROM book_table WHERE id= :id")
    fun getById(id: Int ): Book

    @Update
    suspend  fun updateBook(book: Book)

    @Insert
    suspend  fun addBook( book: Book)

    @Delete
    suspend  fun deleteBook( book: Book)

    @Query ("DELETE FROM book_table")
    suspend fun deleteAllBooks()
}