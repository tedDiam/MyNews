package com.theo.mynews.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.theo.mynews.R
import com.theo.mynews.adapters.RecyclerViewAdapter
import com.theo.mynews.services.ApiClient
import com.theo.mynews.services.HttpConstants
import com.theo.mynews.models.TopStories
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ScienceFragment : Fragment(), Callback<TopStories> {

    var recyclerView: RecyclerView?= null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_science, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView!!.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        ApiClient.apiInterface.getScienceArticles(HttpConstants.API_KEY).enqueue(this)

        return view
    }

    override fun onFailure(call: Call<TopStories>?, t: Throwable?) {
        Toast.makeText(activity, "Une erreur s'est produite!", Toast.LENGTH_SHORT).show()
    }


    override fun onResponse(call: Call<TopStories>?, response: Response<TopStories>?) {
        if (response!!.isSuccessful) {
            val recyclerAdapter = activity?.let { RecyclerViewAdapter(it, response.body()?.results!!) }
            recyclerView!!.adapter = recyclerAdapter
        }
    }



    
}


