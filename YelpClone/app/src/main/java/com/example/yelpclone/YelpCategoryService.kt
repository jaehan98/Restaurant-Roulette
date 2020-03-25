package com.example.yelpclone

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Query

interface YelpCategoryService {
    @GET("categories")



    fun searchCategories(
        @Header("Authorization") authHeader: String
        //@Query("locale") locale: String) : Call<Any>
    ) : Call<YelpSearchResult>


}