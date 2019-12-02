package com.theo.mynews.adapters

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract.CommonDataKinds.Relation.TYPE_FRIEND
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
import com.theo.mynews.holder.BaseViewHolder
import com.theo.mynews.models.Articles
import com.theo.mynews.models.MostPopular
import com.theo.mynews.models.TopStories

class RecyclerViewAdapter: RecyclerView.Adapter<BaseViewHolder<*>> {


    var context: Context? = null
    var item: List<Any>? = null

    constructor(recyclerViewActivity: Context, List: List<Any>) {
        context = recyclerViewActivity
        item = List
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {

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
            TYPE_SEARCHED_ARTICLES -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.article_row, parent, false)
                SearchedArticlesViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }


    }

    override fun getItemCount(): Int {
        return item?.size!!
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {

        when (holder) {
            is TopStoriesViewHolder -> holder.bind(item!![position] as TopStories.Results)
            is MostPopularViewHolder -> holder.bind(item!![position] as MostPopular.Results)
            is SearchedArticlesViewHolder -> holder.bind(item!![position] as Articles.Results)
            else -> throw IllegalArgumentException()
        }

    }

    override fun getItemViewType(position: Int): Int {
        val comparable = item!![position]
        return when (comparable) {
            is TopStories.Results -> TYPE_TOP_STORIES
            is MostPopular.Results -> TYPE_MOST_POPULAR
            is Articles.Results -> TYPE_SEARCHED_ARTICLES
            else -> throw IllegalArgumentException("Invalid type of data " + position)
        }
    }



    class TopStoriesViewHolder(itemView: View?): BaseViewHolder<TopStories.Results>(itemView) {


        var section_list: TextView? = itemView?.findViewById<TextView>(R.id.section_text)

        var date_list: TextView? = itemView?.findViewById<TextView>(R.id.date_text)
        var title_list: TextView? = itemView?.findViewById<TextView>(R.id.title_text)
        var image_list: ImageView? = itemView?.findViewById<ImageView>(R.id.image)
        var card_list: CardView? = itemView?.findViewById<CardView>(R.id.card_view)

        override fun bind(item: TopStories.Results) {
            //var res: List<TopStories.Results>? = null

            section_list?.setText(item.section)
            title_list?.setText(item!!.title)
            date_list?.setText((item.published_date))

            card_list?.setOnClickListener {
                val  intent = Intent(it.context, DetailActivity::class.java)
                intent.putExtra("url", item!!.url)
                it.context.startActivity(intent)

            }
            item!!.multimedia?.let {
                if (it.isNotEmpty()) {
                    image_list?.loadUrl(it.first().url as String)
                }
            }

        }


        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }


    }

    class MostPopularViewHolder(itemView: View?): BaseViewHolder<MostPopular.Results>(itemView) {

        var section_list: TextView? = itemView?.findViewById<TextView>(R.id.section_text)
        var date_list: TextView? = itemView?.findViewById<TextView>(R.id.date_text)
        var title_list: TextView? = itemView?.findViewById<TextView>(R.id.title_text)
        var image_list: ImageView? = itemView?.findViewById<ImageView>(R.id.image)
        var card_list: CardView? = itemView?.findViewById<CardView>(R.id.card_view)

        override fun bind(item: MostPopular.Results) {

            title_list?.setText(item.title)
            section_list?.setText(item.section)
            date_list?.setText((item.published_date))

            card_list?.setOnClickListener {
                val  intent = Intent(it.context, DetailActivity::class.java)
                intent.putExtra("url", item.url)
                it.context.startActivity(intent)

            }

            item!!.media!!.first().media_metadata?.first().let {
                image_list?.loadUrl(it?.url.toString())
            }
        }


        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }



    }

    class SearchedArticlesViewHolder(itemView: View?): BaseViewHolder<Articles.Results>(itemView) {
        var section_list: TextView? = itemView?.findViewById<TextView>(R.id.section_text)
        var date_list: TextView? = itemView?.findViewById<TextView>(R.id.date_text)
        var title_list: TextView? = itemView?.findViewById<TextView>(R.id.title_text)
        var image_list: ImageView? = itemView?.findViewById<ImageView>(R.id.image)
        var card_list: CardView? = itemView?.findViewById<CardView>(R.id.card_view)

        override fun bind(item: Articles.Results) {

            title_list?.setText(item.title)
            section_list?.setText(item.section)
            date_list?.setText((item.published_date))

            card_list?.setOnClickListener {
                val  intent = Intent(it.context, DetailActivity::class.java)
                intent.putExtra("url", item.url)
                it.context.startActivity(intent)

            }

            item.media!!.first().media_metadata?.first().let {
                image_list?.loadUrl(it?.url.toString())

            }

        }
        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }

    }


    companion object {
        private const val TYPE_TOP_STORIES = 0
        private const val TYPE_MOST_POPULAR = 1
        private const val TYPE_SEARCHED_ARTICLES = 2
    }


}