package com.example.raka.testtoko.newslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Filter
import android.widget.Filterable
import com.example.raka.testtoko.R
import com.example.raka.testtoko.model.ArticlesItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*

class NewsListAdapter(val data : List<ArticlesItem>): Filterable, RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
    lateinit var listener: OnItemClickListener
    //  var countryList : List<Country>? = null
    var newsListFilter = data
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return newsListFilter.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(newsListFilter[position])
        val country = newsListFilter[position]
        holder.itemView.setOnClickListener {
            listener.onClick(it, country, position)
        }
    }
    interface OnItemClickListener {
        fun onClick(view: View, articles: ArticlesItem, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(news : ArticlesItem){
            itemView.tv_item_title.text = news.title
            itemView.tv_item_date_detail.text = news.publishedAt
            itemView.tv_item_description.text = news.description
            Picasso.get().load(news.urlToImage).into(itemView.iv_item_news)
        }
    }
    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                if (charString.isEmpty()) {
                    newsListFilter = data
                } else {

                    val filteredList = ArrayList<ArticlesItem>()
                    for (row in data) {
                        if (row.title!!.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }
                    newsListFilter = filteredList
                }
                val filterResult = FilterResults()
                filterResult.values = newsListFilter
                return filterResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                newsListFilter = results?.values as List<ArticlesItem>
                notifyDataSetChanged()
            }

        }
    }
}