package com.example.testandroidapp

import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testandroidapp.Adapter.CategoryAdapter
import com.example.testandroidapp.Api.Common.Common
import com.example.testandroidapp.Api.Interface.RetrofitInterface
import com.example.testandroidapp.Api.Model.Category
import com.example.testandroidapp.Api.Model.Example
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CategoryActivity : AppCompatActivity() {

    private val TAG: String = "TAG"
    lateinit var apiInterface: RetrofitInterface
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: CategoryAdapter
    lateinit var dialog: Dialog
    private lateinit var recyclerCategoryList: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val actionBar = supportActionBar
        actionBar?.setTitle(R.string.activity_categoies)

        /*actionBar?.setDisplayHomeAsUpEnabled(false)
        toolbar = findViewById(R.id.toolbar_category)
        toolbar.setTitle(R.string.activity_categoies)*/

        apiInterface = Common.retrofitServicesCategory

        recyclerCategoryList = findViewById(R.id.recyclerview_category)
        recyclerCategoryList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerCategoryList.layoutManager = layoutManager

        getExample()
    }


    private fun getExample() {
        dialog = ProgressDialog.show(this, "", "Loading...", true)

        apiInterface.getExample().enqueue(object : Callback<Example> {

            override fun onResponse(call: Call<Example>, response: Response<Example>) {
                val myMutableList = response.body()?.data?.categories as MutableList<Category>
                adapter = CategoryAdapter(baseContext, myMutableList)
                adapter.notifyDataSetChanged()
                recyclerCategoryList.adapter = adapter

                dialog.dismiss()
            }

            override fun onFailure(call: Call<Example>, t: Throwable) {
                Log.e(TAG, "error: $t")
                Log.e(TAG, "call: $call")
                Toast.makeText(this@CategoryActivity, "Error: $t", Toast.LENGTH_LONG).show()
            }
        })
    }

}