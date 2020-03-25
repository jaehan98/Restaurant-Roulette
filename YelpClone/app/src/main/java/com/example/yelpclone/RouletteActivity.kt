package com.example.yelpclone

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_roulette.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "RouletteActivity"
class RouletteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roulette)

        val restaurantList = mutableListOf<YelpRestaurant>()



        //Set up retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
               //Call Yelp API
        val yelpService = retrofit.create(YelpService::class.java)
        yelpService.searchRestaurants("Bearer $API_KEY", inputCategory, inputLocation).enqueue(object : Callback<YelpSearchResult>{

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
                    restaurantList.addAll(body.restaurants)
                    Log.i(TAG, "Added " + restaurantList.size + " restaurants")
                    //Set up display
                    val rnd = (0 until restaurantList.size).random()
                    val i = Intent(Intent.ACTION_VIEW, Uri.parse(restaurantList[rnd].url))

                    startActivity(i)

                }


            }

            override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                Log.i(TAG,"onFailure $t")
            }
        })

    }

}
