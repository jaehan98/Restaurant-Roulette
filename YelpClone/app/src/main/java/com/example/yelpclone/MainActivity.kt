package com.example.yelpclone

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


var searchTerm = ""
var inputLocation = ""
var inputCategory = ""
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val categoryList = mutableListOf<YelpCategoryList>()

        //Set up retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //Call Yelp API
        val yelpService = retrofit.create(YelpCategoryService::class.java)
        yelpService.searchCategories("Bearer $API_KEY").enqueue(object : Callback<YelpSearchResult>{

            override fun onResponse(
                call: Call<YelpSearchResult>,
                response: Response<YelpSearchResult>

            ) {
                Log.i(TAG,"onResponse $response")

                val body = response.body()
                if (body == null){
                    Log.w(TAG, "Did not receive valid response body from Yelp API... exiting")
                }
                if (body != null) {
                    categoryList.addAll(body.listOfCategories)
                    val category = categoryList[0].title
                    editCategory.setText(category)

                }


            }

            override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                Log.i(TAG,"onFailure $t")
            }
        })




    }



}
