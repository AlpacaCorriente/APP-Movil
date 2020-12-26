package com.alpaca.mpaginas.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alpaca.mpaginas.R
import com.alpaca.mpaginas.model.Book
import com.alpaca.mpaginas.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment :Fragment(){
    private lateinit var mBookViewModel: BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        view.button_add.setOnClickListener{
            insertDataToDatabase()
        }
    return view
    }
    //funcion para agregar un libro a la base de datos
    private fun insertDataToDatabase() {
        val titulo = ditTextTitleBook.text.toString()
        val autor =  ditTextAutorBook.text.toString()
        val pages = ditTextPagesBook.text
        if(inputCheck(titulo,autor,pages )){

            val book= Book(
                0,
                titulo,
                autor,
                Integer.parseInt(pages.toString()),
                0
            )
            // agregar Libro a la Base de datos
            mBookViewModel.addBook(book)
            Toast.makeText(requireContext(), "Libro Agreado con exito", Toast.LENGTH_LONG ).show()

            // luego de agregar el libro vuelve a la panatalla principal
            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        }else{
            Toast.makeText(requireContext(), "Por favor complete todos los campos", Toast.LENGTH_LONG ).show()
        }

    }
    private fun inputCheck(titulo: String, autor:String, pages: Editable ):Boolean{
        return !(TextUtils.isEmpty((titulo)) && TextUtils.isEmpty((autor)) && pages.isEmpty())

    }


}