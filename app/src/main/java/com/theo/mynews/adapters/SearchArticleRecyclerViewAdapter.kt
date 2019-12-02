package com.theo.mynews.adapters

import android.content.Context
import android.content.Intent
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

class SearchArticleRecyclerViewAdapter: RecyclerView.Adapter<SearchArticleRecyclerViewAdapter.ViewHolder> {

    var context: Context? = null
    var item: List<MostPopular.Results>? = null
    var image: List<MostPopular.Results.Media>? = null


    constructor(recyclerViewActivity: Context, List: List<MostPopular.Results>) {
        context = recyclerViewActivity
        item = List
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchArticleRecyclerViewAdapter.ViewHolder {
        //To change body of created functions use File | Settings | File Templates.
        var view = LayoutInflater.from(context).inflate(R.layout.article_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return item?.size!!
    }
    override fun onBindViewHolder(holder: SearchArticleRecyclerViewAdapter.ViewHolder, position: Int) {
        holder?.title_list?.setText(item!![position].title)
        holder?.section_list?.setText(item!![position].section)
        holder?.date_list?.setText((item!![position].published_date))

        holder?.card_list?.setOnClickListener {
            val  intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra("url", item!![position].url)
            it.context.startActivity(intent)

        }
        image!![position].media_metadata?.let {
            if (it.isNotEmpty()) {
                holder.image_list?.loadUrl(it.first().url as String)
            }
        }

    }



    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var section_list: TextView? = itemView?.findViewById<TextView>(R.id.section_text)
        var date_list: TextView? = itemView?.findViewById<TextView>(R.id.date_text)

        var title_list: TextView? = itemView?.findViewById<TextView>(R.id.title_text)
        var image_list: ImageView? = itemView?.findViewById<ImageView>(R.id.image)
        var card_list: CardView? = itemView?.findViewById<CardView>(R.id.card_view)

    }
    fun ImageView.loadUrl(url: String) {
        Picasso.with(context).load(url).into(this)
    }
}