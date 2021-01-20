package com.alpaca.mpaginas.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.alpaca.mpaginas.R
import com.alpaca.mpaginas.model.Book
import kotlinx.android.synthetic.main.fila_custom.view.*
import com.squareup.picasso.Picasso

class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>(){

    private var bookList= emptyList<Book>()
    var fragmentUser=2

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fila_custom, parent, false))
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // se recupera el libro que se quiere mostrar
        val currentItem= bookList[position]
        holder.itemView.titulo.text=currentItem.title
        holder.itemView.autor.text= currentItem.autor
        Picasso.get().load("https://cdn.icon-icons.com/icons2/390/PNG/512/black-book_38572.png").into(holder.itemView.imageCoverView)
        if(fragmentUser==2){
            holder.itemView.paginas.text= currentItem.currentPage.toString()+"/"+currentItem.pages.toString()
        }else
        {
            holder.itemView.paginas.text= currentItem.pages.toString()
        }

        // esto hace que la lista que despliega los libros pueda ser clickeada y desencadenar una acción en este caso es llamar a update
        holder.itemView.filaLayout.setOnClickListener{
            var action =ListFragmentDirections.actionListFragmentToUpdateCurrentPageFragment(currentItem)
            if(fragmentUser==0){
                action= listBibliotecaDirections.actionListBibliotecaToUpdateFragment(currentItem)
            }
            else if(fragmentUser==1){
                action= listLeidosDirections.actionListLeidosToUpdateFragment(currentItem)
            }
            holder.itemView.findNavController().navigate(action)
        }
    }
    fun setData(book: List<Book>){
        this.bookList=book
        notifyDataSetChanged()
    }
    fun setUser(user: Int){
        fragmentUser=user
    }


}