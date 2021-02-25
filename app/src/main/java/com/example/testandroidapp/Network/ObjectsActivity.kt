package com.example.testandroidapp.Network

import android.app.Dialog
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testandroidapp.R

class ObjectsActivity : AppCompatActivity() {

    private val TAG: String = "TAG"

    lateinit var layoutManager: LinearLayoutManager
    lateinit var dialog: Dialog
    private lateinit var recyclerObjectsList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objects)

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

    }

/*    override fun onPause() {
        super.onPause()
        dialog.dismiss()
    }*/
}