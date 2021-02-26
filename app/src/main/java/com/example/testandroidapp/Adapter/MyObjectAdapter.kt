package com.example.testandroidapp.Adapter

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.util.Log
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
import java.util.*
import kotlin.time.days

class MyObjectAdapter(private val context: Context, private val objectList: MutableList<Object>) :
    RecyclerView.Adapter<MyObjectAdapter.MyViewHolderObjects>() {

    class MyViewHolderObjects(myItemView: View) : RecyclerView.ViewHolder(myItemView) {
        //todo: не факт что работает
        var item: ConstraintLayout = myItemView.findViewById(R.id.item_objects)
        var name: TextView = myItemView.findViewById(R.id.txt_name_objects)
        var des: TextView = myItemView.findViewById(R.id.txt_des_objects)
        var image: ImageView = myItemView.findViewById(R.id.image_objects)

        fun bindObjects(listItem: Object) {
            item.setOnClickListener { v: View ->
                Toast.makeText(v.context, "Работает с ${listItem.workingHours?.get(0)?.from} до ${listItem.workingHours?.get(0)?.to}", Toast.LENGTH_SHORT).show()

//                val uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?&daddr=${listItem.lat},${listItem.lon}")
                val mapIntent = Intent(ACTION_VIEW, Uri.parse("http://maps.google.com/maps?&daddr=${listItem.lat},${listItem.lon}"))
                //todo: Проверка на наличие подходящих приложений
                v.context.startActivity(mapIntent)
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

        Picasso.get().load(objectList[position].image).into(holder.image)
        holder.name.text = objectList[position].name
        holder.des.text = objectList[position].description
    }

    override fun getItemCount(): Int = objectList.size

}