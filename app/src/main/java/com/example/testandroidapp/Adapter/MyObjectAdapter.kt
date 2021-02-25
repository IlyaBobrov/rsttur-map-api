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
import com.example.testandroidapp.Api.Model.Objects
import com.example.testandroidapp.R
import com.squareup.picasso.Picasso

class MyObjectAdapter(private val context: Context, private val objectsList: MutableList<Objects>) :
    RecyclerView.Adapter<MyObjectAdapter.MyViewHolderObjects>() {

    class MyViewHolderObjects(myItemView: View) : RecyclerView.ViewHolder(myItemView) {
        //todo: не факт что работает
        internal lateinit var item_objects: ConstraintLayout
        internal lateinit var txt_des_objects: TextView
        internal lateinit var icon_objects: ImageView

        fun bindObjects(listItem: Objects) {
            item_objects.setOnClickListener { v: View ->
                Toast.makeText(v.context, "${listItem.workingHours}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyObjectAdapter.MyViewHolderObjects {
        val createItemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_objects, parent, false)
        return MyViewHolderObjects(createItemView)

    }

    override fun onBindViewHolder(holder: MyObjectAdapter.MyViewHolderObjects, position: Int) {
        val listItem = objectsList[position]
        holder.bindObjects(listItem)

        Picasso.get().load(objectsList[position].icon).into(holder.icon_objects)
        holder.txt_des_objects.text = objectsList[position].name
        holder.txt_des_objects.text = objectsList[position].description
    }

    override fun getItemCount(): Int = objectsList.size

}