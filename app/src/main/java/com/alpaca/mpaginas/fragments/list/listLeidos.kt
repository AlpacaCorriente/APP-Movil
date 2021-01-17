package com.alpaca.mpaginas.fragments.list


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alpaca.mpaginas.R
import com.alpaca.mpaginas.model.Book
import com.alpaca.mpaginas.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class listLeidos :Fragment() {
    private lateinit var mBookViewModel: BookViewModel
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        // Recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // book view model
        mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        mBookViewModel.getReadsBook.observe(viewLifecycleOwner, Observer { book ->
            adapter.setData(book)
        })

        view.floatingActionButton.setOnClickListener {
            // al apretar el boton de agregar aparece el dialogo
            addBook()
        }
        return view
    }

    // dialogo para seleccionar como se agregará un libro
    private fun addBook() {
        val book = Book(
                id = 0,
                title = "",
                autor = "",
                pages = 0,
                currentPage = 0,
                state = 0
        )
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Agregar libro")
        builder.setItems(arrayOf("Manualmente", "Buscar"), DialogInterface.OnClickListener { dialog, which ->

            if (which == 0) {
                val action = listLeidosDirections.actionListLeidosToAddFragment(book)
                findNavController().navigate(action)
            } else {
                findNavController().navigate(R.id.action_listLeidos_to_searchFragment)
            }
        })

        builder.create().show()

    }
}
