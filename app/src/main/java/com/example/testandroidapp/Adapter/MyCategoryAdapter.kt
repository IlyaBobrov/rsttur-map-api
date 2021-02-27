package com.example.testandroidapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.testandroidapp.Api.Model.Category
import com.example.testandroidapp.ObjectsActivity
import com.example.testandroidapp.R
import com.makeramen.roundedimageview.RoundedImageView

class MyCategoryAdapter(
    private val context: Context,
    private val categoryList: MutableList<Category>,
) :
    RecyclerView.Adapter<MyCategoryAdapter.MyViewHolderCategory>() {

    class MyViewHolderCategory(myItemView: View) : RecyclerView.ViewHolder(myItemView) {
        val item =
            myItemView.findViewById<ConstraintLayout>(R.id.item_category)!! //todo: need code review
        val name: TextView = myItemView.findViewById(R.id.txt_name_category)
        val count: TextView = myItemView.findViewById(R.id.txt_count_category)
        var color: RoundedImageView = myItemView.findViewById(R.id.color_category)

        fun bindCategory(listItem: Category) {
            item.setOnClickListener { v: View ->
                val intent: Intent = Intent(v.context, ObjectsActivity::class.java)
                intent.putExtra("TYPE", "${listItem.type}")
                v.context.startActivity(intent)
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
        holder.bindCategory(listItem)
        holder.name.text = categoryList.get(position).name
        holder.color.setBackgroundResource(convertColor(categoryList.get(position).color.toString()))
        holder.count.text = categoryList.get(position).count.toString()

    }

    private fun convertColor(color: String?): Int {
        return when (color) {
            "warning" -> R.color.warning
            "success" -> R.color.success
            "danger" -> R.color.danger
            "secondary" -> R.color.secondary
            else -> 0x00000000
        }
    }

    override fun getItemCount(): Int = categoryList.size
}