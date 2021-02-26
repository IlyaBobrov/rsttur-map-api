package com.example.testandroidapp

import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testandroidapp.Adapter.MyObjectAdapter
import com.example.testandroidapp.Api.Common.Common2
import com.example.testandroidapp.Api.Interface.RetrofitInterface
import com.example.testandroidapp.Api.Model.Example
import com.example.testandroidapp.Api.Model.Object
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ObjectsActivity : AppCompatActivity() {
    private val TAG: String = "TAG"
    lateinit var api: RetrofitInterface
    lateinit var layoutManager: LinearLayoutManager
    lateinit var dialog: Dialog
    lateinit var adapter: MyObjectAdapter
    private lateinit var recyclerObjectsList: RecyclerView
    private var typeExtra: Any? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objects)

        val actionBar = supportActionBar
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setTitle(R.string.activity_objects)



        ////todo: получить объект -> избавиться от запроса
        api = Common2.retrofitServicesCategory

//        Log.d(TAG,"***${Example.instance}")
//        Log.d(TAG,"***************** ")

        recyclerObjectsList = findViewById(R.id.recyclerview_objects)
        recyclerObjectsList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerObjectsList.layoutManager = layoutManager

        val i = intent
        typeExtra = i.getStringExtra("TYPE")!!
        //var myExample = i.getParcelableExtra("RESPONSE")

        //todo: need tests

        api.getExample()

        getExample();
    }

    private fun getExample() {
        dialog = ProgressDialog.show(this, "", "Loading...", true)

        api.getExample().enqueue(object : Callback<Example> {

            override fun onResponse(call: Call<Example>, response: Response<Example>) {

                var myResponse = response.body()
                val myMutableList: MutableList<Object> =
                    response.body()?.data?.objects as MutableList<Object>

                Log.d(TAG, "1 response (size): ${response.body()?.data?.objects?.size}")
                Log.d(TAG, "4.0 response: ${response.body()?.data?.categories}")

                Log.d(TAG, "myMutableList (icon object 0): ${myMutableList[0].image}")


                adapter = MyObjectAdapter(this@ObjectsActivity,
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