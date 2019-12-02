package com.theo.mynews.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.theo.mynews.DetailActivity
import com.theo.mynews.R
import com.theo.mynews.models.MostPopular
import com.theo.mynews.models.TopStories

class NewRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>{



    var context: Context? = null
    var item: List<Any>? = null


    constructor(recyclerViewActivity: Context, List: List<Any>) {
        context = recyclerViewActivity
        item = List
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{

        return when (viewType) {
            TYPE_TOP_STORIES -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.article_row, parent, false)
                TopStoriesViewHolder(view)

            }
            TYPE_MOST_POPULAR -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.article_row, parent, false)
                MostPopularViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }


    }

    override fun getItemCount(): Int {
        return item?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is TopStoriesViewHolder -> onBindTopStories(holder, item!![position]  as TopStories.Results)
            is MostPopularViewHolder -> onBindMostPopular(holder, item!![position] as MostPopular.Results)
            else -> throw IllegalArgumentException()
        }

    }

    fun ImageView.loadUrl(url: String) {
        Picasso.with(context).load(url).into(this)
    }

    private fun onBindTopStories(holder: TopStoriesViewHolder, row: TopStories.Results){

        //val hold = holder as RecyclerViewAdapter.TopStoriesViewHolder



         holder.section?.setText(row.section)
         holder.title?.setText(row.title)
         holder.date?.setText((row.published_date))

         holder.card?.setOnClickListener {
             val  intent = Intent(it.context, DetailActivity::class.java)
             intent.putExtra("url", row.url)
             it.context.startActivity(intent)

         }
         row.multimedia?.let {
             if (it.isNotEmpty()) {
                 holder.image_list?.loadUrl(it.first().url as String)
             }
         }

    }

    private  fun onBindMostPopular(holder: MostPopularViewHolder, row: MostPopular.Results) {

        //val hold = holder as RecyclerViewAdapter.MostPopularViewHolder
        //holder.card_list

        holder.title_list?.setText(row.title)
        holder.section_list?.setText(row.section)
        holder.date_list?.setText((row.published_date))

        holder.card_list?.setOnClickListener {
            val  intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra("url", row.url)
            it.context.startActivity(intent)

        }

        row.media!!.first().media_metadata?.first().let {
            Log.e("fkjt", "${row.media?.first()?.media_metadata?.first()}")
            holder.image_list?.loadUrl(it?.url.toString())
        }
    }

    override fun getItemViewType(position: Int): Int {
        val comparable = item!![position]
        println(comparable)
        return when (comparable) {
            is TopStories.Results -> TYPE_TOP_STORIES
            is MostPopular.Results -> TYPE_MOST_POPULAR
            else -> throw IllegalArgumentException("Invalid type of data " + position)
        }
    }



    class TopStoriesViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {


        var section: TextView? = itemView?.findViewById<TextView>(R.id.section_text)
        var date: TextView? = itemView?.findViewById<TextView>(R.id.date_text)
        var title: TextView? = itemView?.findViewById<TextView>(R.id.title_text)
        var image_list: ImageView? = itemView?.findViewById<ImageView>(R.id.image)
        var card: CardView? = itemView?.findViewById<CardView>(R.id.card_view)


    }


    class MostPopularViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {

        var section_list: TextView? = itemView?.findViewById<TextView>(R.id.section_text)
        var date_list: TextView? = itemView?.findViewById<TextView>(R.id.date_text)
        var title_list: TextView? = itemView?.findViewById<TextView>(R.id.title_text)
        var image_list: ImageView? = itemView?.findViewById<ImageView>(R.id.image)
        var card_list: CardView? = itemView?.findViewById<CardView>(R.id.card_view)

    }


    companion object {
        private const val TYPE_TOP_STORIES = 0
        private const val TYPE_MOST_POPULAR = 1
    }

}