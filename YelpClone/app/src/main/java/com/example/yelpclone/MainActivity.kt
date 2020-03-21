package com.example.yelpclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


var searchTerm = ""
var inputLocation = ""
var inputCategory = ""
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    fun search(view: View){
        searchTerm = editSearch.text.toString()
        inputLocation = editLocation.text.toString()
        if (searchTerm == ""){
            Toast.makeText(this, "Please enter a term", Toast.LENGTH_SHORT).show();
            return
        }
        if (inputLocation == ""){
            Toast.makeText(this, "Please enter a location", Toast.LENGTH_SHORT).show();
            return
        }

        val intent = Intent(this, RestaurantListActivity::class.java).apply {
        }
        startActivity(intent)
    }

    fun randomize(view: View){
        inputCategory = editCategory.text.toString()
        inputLocation = editLocation.text.toString()

        if (inputCategory == ""){
            Toast.makeText(this, "Please enter a category or hit randomize", Toast.LENGTH_SHORT).show();
            return
        }
        if (inputLocation == ""){
            Toast.makeText(this, "Please enter a location", Toast.LENGTH_SHORT).show();
            return
        }

        val intent = Intent(this, RouletteActivity::class.java).apply {
        }
        startActivity(intent)
    }
}
