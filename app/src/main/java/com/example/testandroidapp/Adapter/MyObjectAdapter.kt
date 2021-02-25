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
import com.example.testandroidapp.Api.Model.Object
import com.example.testandroidapp.R
import com.squareup.picasso.Picasso

class MyObjectAdapter(private val context: Context, private val objectList: MutableList<Object>) :
    RecyclerView.Adapter<MyObjectAdapter.MyViewHolderObjects>() {

    class MyViewHolderObjects(myItemView: View) : RecyclerView.ViewHolder(myItemView) {
        //todo: не факт что работает
        var item: ConstraintLayout = myItemView.findViewById(R.id.item_objects)
        var name: TextView = myItemView.findViewById(R.id.txt_name_objects)
        var des: TextView = myItemView.findViewById(R.id.txt_des_objects)
        var icon: ImageView = myItemView.findViewById(R.id.icon_objects)

        fun bindObjects(listItem: Object) {
            item.setOnClickListener { v: View ->
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

        val listItem = objectList[position]
        holder.bindObjects(listItem)

        Picasso.get().load(objectList[position].icon).into(holder.icon)
        holder.name.text = objectList[position].name
        holder.des.text = objectList[position].description
    }

    override fun getItemCount(): Int = objectList.size

}