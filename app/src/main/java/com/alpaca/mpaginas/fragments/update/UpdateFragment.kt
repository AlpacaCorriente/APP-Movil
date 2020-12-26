package com.alpaca.mpaginas.fragments.update

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
import com.alpaca.mpaginas.model.Book
import com.alpaca.mpaginas.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mBookViewModel: BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mBookViewModel=ViewModelProvider(this).get(BookViewModel::class.java)
        // se actulizan los campos para mostrar la informacion del libro seleccionado en la recycler view
        view.updateTextTitleBook.setText(args.currentBook.title)
        view.updateTextAutorBook.setText(args.currentBook.autor)
        view.updateTextPagesBook.setText(args.currentBook.pages.toString())
        view.update_button.setOnClickListener{
            updateItem()
        }
        return view
    }
    // toma los valores puestos en los campos para actualizarlos en la BD
private fun updateItem (){
    val titulo = updateTextTitleBook.text.toString()
    val autor = updateTextAutorBook.text.toString()
    val pagina = Integer.parseInt(updateTextPagesBook.text.toString())
        if (inputCheck(titulo, autor ,updateTextPagesBook.text )){
            // crea el objeto Book
            val updateBook = Book(args.currentBook.id, titulo, autor, pagina, 0)
            // actualiza el libro actual (currentBook)
            mBookViewModel.updateBook(updateBook)
            Toast.makeText(requireContext(), "Se a actualizado el libro", Toast.LENGTH_SHORT).show()
            // envia al vista principal
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        }else{
            Toast.makeText(requireContext(), "Lene todos los campos", Toast.LENGTH_SHORT).show()
        }


}
    private fun inputCheck(titulo: String, autor:String, pages: Editable):Boolean{
        return !(TextUtils.isEmpty((titulo)) && TextUtils.isEmpty((autor)) && pages.isEmpty())

    }

}

