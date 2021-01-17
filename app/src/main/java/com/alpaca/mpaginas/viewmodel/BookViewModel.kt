package com.alpaca.mpaginas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import com.alpaca.mpaginas.data.BookDB
import com.alpaca.mpaginas.repository.BookRepository
import com.alpaca.mpaginas.model.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel (application: Application): AndroidViewModel(application){
    val getAllData: LiveData <List<Book>>
    val getInLibraryBook: LiveData <List<Book>>
    val getReadsBook: LiveData <List<Book>>

    private val repository: BookRepository

    init{
        val bookDao = BookDB.getDatabase(application).bookDao()
        repository = BookRepository(bookDao)
        getAllData = repository.getAllData
        getInLibraryBook=repository.getInLibraryBook
        getReadsBook = repository.getReadsBook

    }
    fun addBook (book: Book){
        viewModelScope.launch(Dispatchers.IO){
            repository.addBook(book)

        }
    }
fun updateBook(book: Book){
    viewModelScope.launch(Dispatchers.IO){
        repository.updateBook(book)
    }
}
    fun deleteBook(book: Book){
        viewModelScope.launch(Dispatchers.IO ){
            repository.deleteBook(book)
        }
    }
    fun deleteAllUsers(){
        viewModelScope.launch(Dispatchers.IO ){
            repository.deleteAllBooks()
        }
    }
}