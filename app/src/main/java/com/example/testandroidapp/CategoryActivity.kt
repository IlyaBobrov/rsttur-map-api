package com.example.testandroidapp

import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testandroidapp.Adapter.MyCategoryAdapter
import com.example.testandroidapp.Api.Common.Common2
import com.example.testandroidapp.Api.Interface.RetrofitInterface
import com.example.testandroidapp.Api.Model.Category
import com.example.testandroidapp.Api.Model.Example
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CategoryActivity : AppCompatActivity() {

    private val TAG: String = "TAG"

    lateinit var apiInterface: RetrofitInterface
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: MyCategoryAdapter
    lateinit var dialog: Dialog
    private lateinit var recyclerCategoryList: RecyclerView

    var myResponse: Example? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        recyclerCategoryList = findViewById(R.id.recyclerview_category)

        apiInterface = Common2.retrofitServicesCategory
        recyclerCategoryList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)

        recyclerCategoryList.layoutManager = layoutManager

        //dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build() as SpotsDialog

        getExample()
    }

    private fun getExample() {
        dialog = ProgressDialog.show(this, "", "Loading...", true)
        //dialog.show()

        apiInterface.getExample().enqueue(object : Callback<Example> {

            override fun onResponse(call: Call<Example>, response: Response<Example>) {

                /*//~~~start Logs debug~~~
                Log.i(TAG, "response ${response.body()?.success.toString()}")

                Log.d(TAG, "1! (name id 1): ${response.body()?.data?.categories?.get(1)?.name}")
                Log.d(TAG, "2!: ${response.body()?.data?.categories.toString()}")
                Log.d(TAG, "3!: ${response.body()?.data?.categories?.toList().toString()}")
                Log.d(TAG, "4!: ${response.body()?.data?.categories?.toMutableList().toString()}")

                Log.d(
                    TAG,
                    "5! (listIterator): ${response.body()?.data?.categories?.listIterator()}"
                )
                Log.d(TAG, "6! (size): ${response.body()?.data?.categories?.size}")

                Log.d(TAG, "true! geo lat: ${response.body()?.data?.geo?.lat}")
                Log.d(TAG, "true! geo lon: ${response.body()?.data?.geo?.lon}")

                Log.d(TAG, "to be! objects id 0: ${response.body()?.data?.objects?.get(0)}")
                //~~~end Logs debug~~~*/

                Log.d(TAG, "response: ${response}")

                myResponse = response.body()
                val myMutableList: MutableList<Category> =
                    myResponse?.data?.categories as MutableList<Category>

                Log.d(TAG, "myMutableList (size): ${myMutableList.size}")

                adapter = MyCategoryAdapter(baseContext, myMutableList)
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