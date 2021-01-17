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
import androidx.navigation.fragment.navArgs
import com.alpaca.mpaginas.R
import com.alpaca.mpaginas.fragments.update.UpdateFragmentArgs
import com.alpaca.mpaginas.model.Book
import com.alpaca.mpaginas.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class AddFragment :Fragment(){

    private val args by navArgs<AddFragmentArgs>()
    private lateinit var mBookViewModel: BookViewModel
    var state = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        view.ditTextTitleBook.setText(args.currentBook.title)
        view.ditTextAutorBook.setText(args.currentBook.autor)
        view.ditTextPagesBook.setText(args.currentBook.pages.toString())


        view.button_add.setOnClickListener{
            if (radioButtonBiblioteca.isChecked){
                state = 0
            }
            else if(radioButtonLeyendo.isChecked){
                state = 1
            }
            else if(radioButtonLeidos.isChecked){
                state=2
            }
            else {
                Toast.makeText(requireContext(), "Seleccione en que seccion guardará el libro", Toast.LENGTH_LONG ).show()
            }

            insertDataToDatabase()
        }
    return view
    }
    //funcion para agregar un libro a la base de datos
    private fun insertDataToDatabase() {
        val titulo = ditTextTitleBook.text.toString()
        val autor =  ditTextAutorBook.text.toString()
        val pages = ditTextPagesBook.text
        val states = state
        if(inputCheck(titulo,autor,pages)){

            val book= Book(
                0,
                titulo,
                autor,
                Integer.parseInt(pages.toString()),
                0,
                state
            )
            // agregar Libro a la Base de datos
            mBookViewModel.addBook(book)
            Toast.makeText(requireContext(), "Libro agregado con exito", Toast.LENGTH_LONG ).show()

            // luego de agregar el libro vuelve a la panatalla principal
            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        }else{
            Toast.makeText(requireContext(), "Por favor complete todos los campos", Toast.LENGTH_LONG ).show()
        }

    }
    // función para comprobar si el input es valido
    private fun inputCheck(titulo: String, autor:String, pages: Editable ):Boolean{
        return !(TextUtils.isEmpty((titulo)) && TextUtils.isEmpty((autor)) && pages.isEmpty())

    }


}