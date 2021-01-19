package com.alpaca.mpaginas.fragments.UpdateCurrentPage

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alpaca.mpaginas.R
import com.alpaca.mpaginas.fragments.update.UpdateFragmentArgs
import com.alpaca.mpaginas.model.Book
import com.alpaca.mpaginas.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlinx.android.synthetic.main.fragment_update_current_page.*
import kotlinx.android.synthetic.main.fragment_update_current_page.view.*


class UpdateCurrentPageFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mBookViewModel: BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_current_page, container, false)

        mBookViewModel= ViewModelProvider(this).get(BookViewModel::class.java)
        // se actulizan los campos para mostrar la informacion del libro seleccionado en la recycler view
        view.TextTitleBook.setText(args.currentBook.title)
        view.TextAutorBook.setText(args.currentBook.autor)
        view.TextPagesBook.setText(args.currentBook.pages.toString())
        view.TextCurrentPage.setText(args.currentBook.currentPage.toString())

        view.guardar_button.setOnClickListener{
           updateItem()
        }

        //se agrega el menu
        setHasOptionsMenu(true)
        return view
    }

    // toma los valores puestos en los campos para actualizarlos en la BD
    private fun updateItem (){
        val titulo = TextTitleBook.text.toString()
        val autor =  TextAutorBook.text.toString()
        val pagina = Integer.parseInt(TextPagesBook.text.toString())
        val currentPage = Integer.parseInt(TextCurrentPage.text.toString())

        val updateBook = Book(args.currentBook.id, titulo, autor, pagina, currentPage,1)
        // actualiza el libro actual (currentBook)
        mBookViewModel.updateBook(updateBook)
        // envia al vista principal
        findNavController().navigate(R.id.action_updateCurrentPageFragment_to_listFragment)



    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId== R.id.menu_delete){
            deleteBook()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteBook() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Si"){_, _ ->
            mBookViewModel.deleteBook(args.currentBook)
            Toast.makeText(requireContext(),"Libro borrado exitosamente", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Borrar" )
        builder.setMessage("Seguro de borrar ${args.currentBook.title}?")
        builder.create().show()

    }
}