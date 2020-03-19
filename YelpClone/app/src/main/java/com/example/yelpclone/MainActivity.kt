package com.example.yelpclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "MainActivity"
private const val BASE_URL = "https://api.yelp.com/v3/"
private const val API_KEY = "TZIrQEJTwJb4_Fyc6Ph1uKEkhzqizWg9TSTKO0Lyz9fbuRcfQVg4y7ArPvR4VLmlhkSYw6urRhVNwyykTGt8KDuTb3oZ5VPSj4kSx9QoUGL1wvDx4MSJvYxURwBzXnYx"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val yelpService = retrofit.create(YelpService::class.java)
        yelpService.searchRestaurants("Bearer $API_KEY","Avocado Toast","New York").enqueue(object : Callback<YelpSearchResult>{

            override fun onResponse(
                call: Call<YelpSearchResult>,
                response: Response<YelpSearchResult>
            ) {
               Log.i(TAG,"onResponse $response")
            }

            override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                Log.i(TAG,"onFailure $t")
            }
        })




    }
}
