package com.theo.mynews.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.theo.mynews.R

class ArticleSearchFragment : Fragment() {


    private var recycleriew: RecyclerView?= null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_article_search, container, false)
        //recycleriew = view.findViewById(R.id.recycler_view)
        return view
    }


}
