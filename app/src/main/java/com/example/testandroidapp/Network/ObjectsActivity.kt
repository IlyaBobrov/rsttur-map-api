package com.example.testandroidapp.Network

import android.app.Dialog
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testandroidapp.Adapter.MyObjectAdapter
import com.example.testandroidapp.Api.Common.Common2
import com.example.testandroidapp.Api.Interface.RetrofitInterface
import com.example.testandroidapp.Api.Model.Example
import com.example.testandroidapp.Api.Model.Object
import com.example.testandroidapp.R
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objects)

        api = Common2.retrofitServicesCategory
        recyclerObjectsList = findViewById(R.id.recyclerview_objects)
        recyclerObjectsList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerObjectsList.layoutManager = layoutManager

        val i = intent
        val type = i.getStringExtra("TYPE")
//        var myExample = i.getParcelableExtra("RESPONSE")

        Toast.makeText(this, "$type", Toast.LENGTH_LONG).show()
        //todo: need tests
        //dialog = ProgressDialog.show(this, "", getString(R.string.loading), true)
        getExample();
    }

    private fun getExample() {
        dialog = ProgressDialog.show(this, "", "Loading...", true)
        //dialog.show()

        api.getExample().enqueue(object : Callback<Example> {


            override fun onResponse(call: Call<Example>, response: Response<Example>) {

                var myResponse = response.body()
                val myMutableList: MutableList<Object> = response.body()?.data?.objects as MutableList<Object>

                Log.d(TAG, "1 response (size): ${response.body()?.data?.objects?.size}")
                Log.d(TAG, "2 response (last): ${response.body()?.data?.objects?.indices?.last}")
                Log.d(TAG, "3.0 response (data): ${response.body()?.data.toString()}")
//                Log.d(TAG, "3.1 response (fuck): ${(response.body()?.data?.objects as MutableList<Object>).get(0).name as Object}")
                Log.d(TAG, "3.2 response (indices?.first): ${response.body()?.data?.objects?.indices?.first.toString()}")
                Log.d(TAG, "4.0 response: ${response.body()?.data?.categories}" )
                Log.d(TAG, "4.1 response: ${response.body()?.data?.objects}")
                Log.d(TAG, "4.2 response: ${response.body()?.data?.objects}")

                Log.d(TAG, "5 myMutableList (size): ${myMutableList.size}")
                Log.d(TAG, "6 myMutableList: $myMutableList")
//                Log.d(TAG, "myMutableList: ${myMutableList.get(0).name}")
//                Log.d(TAG, "myMutableList: ${myMutableList.get(0).type}")

                adapter = MyObjectAdapter(this@ObjectsActivity, myMutableList)
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

//        api.getExample().isExecuted

    }
/*    override fun onPause() {
        super.onPause()
        dialog.dismiss()
    }*/
}