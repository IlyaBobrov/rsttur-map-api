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
import com.example.testandroidapp.Api.Model.Category
import com.example.testandroidapp.R
import com.squareup.picasso.Picasso

class MyCategoryAdapter(
    private val context: Context,
    private val categoryList: MutableList<Category>
) :
    RecyclerView.Adapter<MyCategoryAdapter.MyViewHolderCategory>() {

    class MyViewHolderCategory(myItemView: View) : RecyclerView.ViewHolder(myItemView) {
        val item = myItemView.findViewById<ConstraintLayout>(R.id.item_category)!! //todo: need code review
        val name: TextView = myItemView.findViewById(R.id.txt_name_category)
        val icon: ImageView = myItemView.findViewById(R.id.icon_category)
        val color: ImageView = myItemView.findViewById(R.id.color_category)
        val count: TextView = myItemView.findViewById(R.id.txt_count_category)

        fun myBind(listItem: Category) {
            item.setOnClickListener { v: View ->
                Toast.makeText(v.context, "${listItem.type}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyCategoryAdapter.MyViewHolderCategory {
        val createItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_category, parent, false)
        return MyViewHolderCategory(createItemView);
    }

    override fun onBindViewHolder(holder: MyViewHolderCategory, position: Int) {
        val listItem = categoryList[position]
        holder.myBind(listItem)

        Picasso.get().load(categoryList[position].icon).into(holder.icon)
        Picasso.get().load(categoryList[position].color).into(holder.color)
        holder.name.text = categoryList.get(position).name
        holder.count.text = categoryList.get(position).count.toString()
    }

    override fun getItemCount(): Int = categoryList.size
}