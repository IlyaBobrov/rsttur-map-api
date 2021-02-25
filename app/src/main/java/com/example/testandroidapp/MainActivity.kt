package com.example.testandroidapp

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testandroidapp.Adapter.MyMovieAdapter
import com.example.testandroidapp.Api.Interface.RetrofitServices
import com.example.testandroidapp.Api.Common.Common1
import com.example.testandroidapp.Api.Model.Movie
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG: String = "TAG"

    //рабочий вариант с Movie
    lateinit var mService: RetrofitServices
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: MyMovieAdapter
    lateinit var dialog: Dialog
    private lateinit var recyclerMovieList: RecyclerView

    lateinit var btnCategory: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onStart")

        btnCategory = findViewById(R.id.btn_category)
        btnCategory.setOnClickListener { v: View ->
            startActivity(Intent(this,CategoryActivity::class.java).apply {})
        }

        //рабочий вариант с Movie
        recyclerMovieList = findViewById(R.id.recyclerMovieList)

        mService = Common1.retrofitServices
        recyclerMovieList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)

        recyclerMovieList.layoutManager = layoutManager

        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build() as SpotsDialog

        getAllMovieList()

        /*
        //не рабочий вариант
        val obj = myObject()
        obj.id = 1
        obj.name = "Name1"
        Log.d("TAG", "Hello, this id:${obj.id}, and name:${obj.name}.")*/
    }

    //рабочий вариант с Movie
    private fun getAllMovieList() {
        dialog.show()

        mService.getMovieList().enqueue(object : Callback<MutableList<Movie>> {
            override fun onResponse(
                call: Call<MutableList<Movie>>,
                response: Response<MutableList<Movie>>
            ) {
                adapter = MyMovieAdapter(baseContext, response.body() as MutableList<Movie>)
                adapter.notifyDataSetChanged()
                recyclerMovieList.adapter = adapter

                dialog.dismiss()
            }

            override fun onFailure(call: Call<MutableList<Movie>>, t: Throwable) {
                Log.e(TAG, "error: $t")
            }
        })
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")

        /*
        //не рабочий вариант
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rsttur.ru/api/base-app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val serverApi: ServerApi = retrofit.create(ServerApi::class.java)

        val categories = serverApi.funCategories()

        categories.enqueue(object : Callback<List<myCategory>> {
            override fun onResponse(
                call: Call<List<myCategory>>,
                response: Response<List<myCategory>>
            ) {
                if (response.isSuccessful) {
                    Log.d("TAG", "isSuccessful")
                } else {
                    Log.d("TAG", "response code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<myCategory>>, t: Throwable) {
                Log.d("TAG", "onFailure: $t")
            }
        })*/
    }

}