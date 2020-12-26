package com.alpaca.mpaginas.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alpaca.mpaginas.R
import com.alpaca.mpaginas.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment :Fragment(){
    private lateinit var mBookViewModel: BookViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_list, container, false)

        // Recyclerview
        val adapter=ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter=adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // book view model
        mBookViewModel=ViewModelProvider(this).get(BookViewModel::class.java)
        mBookViewModel.getAllData.observe(viewLifecycleOwner, Observer{ book ->
            adapter.setData(book)
        })
        view.floatingActionButton.setOnClickListener{
             findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        return view
    }

 }

