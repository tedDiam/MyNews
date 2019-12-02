package com.theo.mynews

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.theo.mynews.adapters.RecyclerViewAdapter
import com.theo.mynews.models.Articles
import com.theo.mynews.services.ApiClient
import com.theo.mynews.services.HttpConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ResultsActivity : AppCompatActivity(), Callback<Articles> {


    var recyclerView: RecyclerView?= null
    //var recyclerAdapter:RecyclerViewAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val searchTerm:String = intent.getStringExtra("search_term")
        val field: String = intent.getStringExtra("field")
        val beginDate: String = intent.getStringExtra("begin_date")
        val endDate: String = intent.getStringExtra("end_date")
        val facet:Boolean = true
        val facet_field:String = "source"

        Log.d("recherche", searchTerm)
        Log.d("domaine", field)
        Log.d("debut", beginDate)
        Log.d("fin", endDate)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        ApiClient.apiInterface.getSearchedArticles(searchTerm, field, facet_field, facet, beginDate, endDate, HttpConstants.API_KEY).enqueue(this)
    }

    override fun onFailure(call: Call<Articles>, t: Throwable) {
        Toast.makeText(this, "Une erreur s'est produite!", Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<Articles>, response: Response<Articles>) {
        if (response.isSuccessful) {
            Log.d("STATUS", response.body()?.status)
            val recyclerAdapter = RecyclerViewAdapter(this, response.body()?.results!!)
            recyclerView!!.adapter = recyclerAdapter

        }
    }

}
