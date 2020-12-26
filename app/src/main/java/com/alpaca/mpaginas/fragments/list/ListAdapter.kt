package com.alpaca.mpaginas.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.alpaca.mpaginas.R
import com.alpaca.mpaginas.model.Book
import kotlinx.android.synthetic.main.fila_custom.view.*

class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>(){

    private var bookList= emptyList<Book>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fila_custom, parent, false))
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem= bookList[position]
        holder.itemView.id_txt.text=currentItem.id.toString()
        holder.itemView.titulo.text=currentItem.title
        holder.itemView.autor.text= currentItem.autor
        holder.itemView.paginas.text=currentItem.pages.toString()
        // esto hace que la lista que despliega los libros pueda se clickeada y desencadenar una accipin en este caso es llamrar a update
        holder.itemView.filaLayout.setOnClickListener{
            val action= ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }
    fun setData(book: List<Book>){
        this.bookList=book
        notifyDataSetChanged()
    }


}