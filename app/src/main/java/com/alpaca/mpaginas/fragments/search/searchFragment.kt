package com.alpaca.mpaginas.fragments.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alpaca.mpaginas.R
import com.alpaca.mpaginas.fragments.list.ListAdapter
import com.alpaca.mpaginas.model.Book
import com.alpaca.mpaginas.viewmodel.BookViewModel
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import org.json.JSONException
import org.json.JSONObject

import com.android.volley.RequestQueue
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*

class searchFragment : Fragment() {
    var book =""
    val adapter=SearchAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        ViewModelProvider(this).get(BookViewModel::class.java)
        // Recyclerview


        val recyclerView = view.search_recyclerview
        recyclerView.adapter=adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        view.button_search.setOnClickListener{
            this.book = editSearch.text.toString()
            searchBook()

        }
        return view
    }

    private  fun searchBook(){
        var foundBooks = ArrayList<Book>()

        val queue = Volley.newRequestQueue(this.context)

        val url = "https://www.googleapis.com/books/v1/volumes?q="+this.book
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener { response->
                val items = response.getJSONArray("items")
                /* it es el JSONArray, iterando sobre este tendremos JSONObjects */
                Log.d("LibrosItem",items.toString())

                for (i in 0 until items.length()) {

                    try {
                        var jsonObject = JSONObject()
                        var infoBook = JSONObject()
                        var image= " "
                        try{
                            jsonObject = items.getJSONObject(i)
                            infoBook =jsonObject.getJSONObject("volumeInfo")
                            /*var infoImage= infoBook.getJSONObject("imageLinks")
                            if(image.toString().length == 2 ){
                                image= "https://i.imgur.com/tGbaZCY.jpg"
                            }
                            else {
                                image= infoImage.getString("thumbnail")
                            }*/
                        }catch (e: JSONException) {
                            e.printStackTrace()
                        }

                        Log.d("VolumeInfo wea",infoBook.toString())
                        Log.d("Image INFO", image )
                        Toast.makeText(requireContext(), this.book, Toast.LENGTH_LONG ).show()
                        Log.d("autors", infoBook.getJSONArray("authors").getString(0) )
                        val book = Book(
                            id=0,
                            title = infoBook.getString("title"),
                            autor=  infoBook.getJSONArray("authors").getString(0),
                            pages = 0,
                            currentPage =0
                        )

                        foundBooks.add(book)

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                }
                this.adapter.setData(foundBooks)
            },
            Response.ErrorListener { error -> Log.d("json", error.toString())}
        )
        queue.add(jsonObjectRequest)
    }
}



