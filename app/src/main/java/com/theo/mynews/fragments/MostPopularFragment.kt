package com.theo.mynews.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.theo.mynews.R
import com.theo.mynews.adapters.RecyclerViewAdapter
import com.theo.mynews.models.MostPopular
import com.theo.mynews.services.ApiClient
import com.theo.mynews.services.HttpConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class MostPopularFragment : Fragment(), Callback<MostPopular> {

    var recyclerView: RecyclerView?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View = inflater.inflate(R.layout.fragment_most_popular, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_most_pop)
        recyclerView!!.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        ApiClient.apiInterface.getMostPopular(HttpConstants.API_KEY).enqueue(this)

        return  view
    }

    override fun onFailure(call: Call<MostPopular>, t: Throwable) {
        Log.d("called_ url", call.request().url().toString())
        Log.d("message : ", t.message)
        Toast.makeText(activity, "Une erreur s'est produite!", Toast.LENGTH_SHORT).show()

    }
    override fun onResponse(call: Call<MostPopular>?, response: Response<MostPopular>?) {
        if (response!!.isSuccessful) {

            var recyclerAdapter = activity?.let { RecyclerViewAdapter(it, response.body()?.results!!) }
            recyclerView!!.adapter = recyclerAdapter
        }
    }


}


