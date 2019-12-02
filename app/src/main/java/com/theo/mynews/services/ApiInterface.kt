package com.theo.mynews.services

import com.theo.mynews.models.Articles
import com.theo.mynews.models.MostPopular
import com.theo.mynews.models.TopStories
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    @GET("topstories/v2/home.json?")
    fun getTopStories(@Query("api-key") api_key:String): Call<TopStories>

    @GET("topstories/v2/science.json?")
    fun getScienceArticles(@Query("api-key") api_key:String): Call<TopStories>

    @GET("mostpopular/v2/emailed/7.json?")
    fun getMostPopular(@Query("api-key") api_key: String): Call<MostPopular>

    @GET("search/v2/articlesearch.json?")
    fun getSearchedArticles(@Query("q")searchTerm:String,  @Query("fl") field: String, @Query("facet_fields") facet_fields: String, @Query("facet")facet:Boolean, @Query("begin_date") begin_date:String, @Query("end_date") end_date:String, @Query("api-key") api_key:String): Call<Articles >
}