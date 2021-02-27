package com.example.testandroidapp

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
//import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testandroidapp.Adapter.ObjectAdapter
import com.example.testandroidapp.Api.Common.Common
import com.example.testandroidapp.Api.Interface.RetrofitInterface
import com.example.testandroidapp.Api.Model.Example
import com.example.testandroidapp.Api.Model.Object
import com.google.android.material.appbar.CollapsingToolbarLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ObjectsActivity : AppCompatActivity() {
    private val TAG: String = "TAG"
    lateinit var api: RetrofitInterface
    lateinit var layoutManager: LinearLayoutManager
    lateinit var dialog: Dialog
    lateinit var adapter: ObjectAdapter
    private lateinit var recyclerObjectsList: RecyclerView
    private var typeExtra: Any? = null
    lateinit var toolbar: Toolbar
    lateinit var collapsingToolbar: CollapsingToolbarLayout

    @SuppressLint("RestrictedApi")
    @SuppressWarnings("ConstantConditions")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objects)

        val actionBar = supportActionBar
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setTitle(R.string.activity_objects)

        /*toolbar = findViewById(R.id.toolbar_objects)
        setSupportActionBar(this.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsingToolbar = findViewById(R.id.collapse_toolbar_object)
        collapsingToolbar.title = getString(R.string.activity_objects)*/

        api = Common.retrofitServicesCategory

        recyclerObjectsList = findViewById(R.id.recyclerview_objects)
        recyclerObjectsList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerObjectsList.layoutManager = layoutManager

        typeExtra = intent.getStringExtra("TYPE")!!

        getExample()
    }

    private fun getExample() {
        dialog = ProgressDialog.show(this, "", "Loading...", true)

        api.getExample().enqueue(object : Callback<Example> {

            override fun onResponse(call: Call<Example>, response: Response<Example>) {
                val myMutableList = response.body()?.data?.objects as MutableList<Object>

                adapter = ObjectAdapter(this@ObjectsActivity,
                    myMutableList.filter { it.type == typeExtra } as MutableList<Object>)
                adapter.notifyDataSetChanged()
                recyclerObjectsList.adapter = adapter

                dialog.dismiss()
            }

            override fun onFailure(call: Call<Example>, t: Throwable) {
                Log.e(TAG, "error: $t")
                Log.e(TAG, "call: $call")
                Toast.makeText(this@ObjectsActivity, "Error: $t", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}