package com.example.testandroidapp.Adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.testandroidapp.Api.Model.mObject

class MyObjectAdapter(private val context: Context, private val objectsList: MutableList<mObject>) :
    RecyclerView.Adapter<MyObjectAdapter.MyViewHolderObjects>() {

    class MyViewHolderObjects(myItemView: View) : RecyclerView.ViewHolder(myItemView){
        internal lateinit var item_objects: ConstraintLayout;
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyObjectAdapter.MyViewHolderObjects {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyObjectAdapter.MyViewHolderObjects, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}