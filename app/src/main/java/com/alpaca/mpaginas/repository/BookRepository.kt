package com.alpaca.mpaginas.repository

import androidx.lifecycle.LiveData
import com.alpaca.mpaginas.data.BookDao
import com.alpaca.mpaginas.model.Book
import kotlinx.coroutines.Dispatchers

class BookRepository(private val bookDao: BookDao) {

    val getAllData: LiveData<List<Book>> = bookDao.getAllData()
    suspend fun addBook (book: Book){

        bookDao.addBook(book)
    }
    suspend fun  updateBook(book: Book){

        bookDao.updateBook(book)
    }
    suspend fun  deleteBook(book:Book){
        bookDao.deleteBook(book)
    }
    suspend fun deleteAllBooks(){
        bookDao.deleteAllBooks()
    }
}