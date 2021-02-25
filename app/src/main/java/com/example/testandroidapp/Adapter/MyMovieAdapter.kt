package com.example.testandroidapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.testandroidapp.Api.Model.Movie
import com.example.testandroidapp.R
import com.squareup.picasso.Picasso

class MyMovieAdapter(private val context: Context, private val movieList: MutableList<Movie>) :
    RecyclerView.Adapter<MyMovieAdapter.MyViewHolderMovie>() {

    class MyViewHolderMovie(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lay: ConstraintLayout = itemView.findViewById(R.id.linearLayout)
        val image: ImageView = itemView.findViewById(R.id.image_movie)
        val text_name: TextView = itemView.findViewById(R.id.txt_name)
        val text_team: TextView = itemView.findViewById(R.id.txt_team)
        val text_createdby: TextView = itemView.findViewById(R.id.txt_createdby)

        fun bind(listItem: Movie) {
            lay.setOnClickListener { v: View ->
                Toast.makeText(
                    v.context,
                    "Нажали на item id: ${listItem.bio}, name: ${listItem.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMovieAdapter.MyViewHolderMovie {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_movie, parent, false)
        return MyViewHolderMovie(itemView)
    }

    override fun onBindViewHolder(holder: MyMovieAdapter.MyViewHolderMovie, position: Int) {
        val listItem = movieList[position]
        holder.bind(listItem)

        Picasso.get().load(movieList[position].imageurl).into(holder.image)
        holder.text_name.text = movieList[position].name
        holder.text_team.text = movieList[position].team
        holder.text_createdby.text = movieList[position].createdby
    }

    override fun getItemCount(): Int = movieList.size

}