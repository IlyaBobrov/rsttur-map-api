package com.example.testandroidapp.Adapter

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.testandroidapp.Api.Model.Object
import com.example.testandroidapp.R
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso


class ObjectAdapter(private val context: Context, private val objectList: MutableList<Object>) :
    RecyclerView.Adapter<ObjectAdapter.MyViewHolderObjects>() {

    class MyViewHolderObjects(myItemView: View) : RecyclerView.ViewHolder(myItemView) {
        var item: ConstraintLayout = myItemView.findViewById(R.id.item_objects)
        var name: TextView = myItemView.findViewById(R.id.txt_name_objects)
        var des: TextView = myItemView.findViewById(R.id.txt_des_objects)
        var image: RoundedImageView = myItemView.findViewById(R.id.image_objects)

        fun bindObjects(listItem: Object) {
            item.setOnClickListener { v: View ->
                //Toast.makeText(v.context, "Работает с ${listItem.workingHours?.get(0)?.from} до ${listItem.workingHours?.get(0)?.to}", Toast.LENGTH_SHORT).show()
                val mapIntent = Intent(
                    ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?&daddr=${listItem.lat},${listItem.lon}")
                )
                //todo: Проверка на наличие подходящих приложений
                v.context.startActivity(mapIntent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ObjectAdapter.MyViewHolderObjects {
        val createItemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_objects, parent, false)
        return MyViewHolderObjects(createItemView)

    }

    override fun onBindViewHolder(holder: ObjectAdapter.MyViewHolderObjects, position: Int) {
        val listItem = objectList[position]
        holder.bindObjects(listItem)

        /*val shader: BitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        val paint = Paint()
        paint.setAntiAlias(true)
        paint.setShader(shader)

        val rect = RectF(0.0f, 0.0f, width, height)

// rect contains the bounds of the shape
// radius is the radius in pixels of the rounded corners
// paint contains the shader that will texture the shape

// rect contains the bounds of the shape
// radius is the radius in pixels of the rounded corners
// paint contains the shader that will texture the shape
        canvas.drawRoundRect(rect, radius, radius, paint)*/

        Picasso.get().load(objectList[position].image).into(holder.image)

        holder.name.text = objectList[position].name
        holder.des.text = objectList[position].description
    }

    override fun getItemCount(): Int = objectList.size

}