package com.example.raka.testtoko.newslist

import com.example.raka.testtoko.model.ArticlesItem

interface NewsListContracts {
    interface view{
        fun closePage()
        fun showProgressBar()
        fun dismissProgessBar()
        fun setAdapter(adapter : NewsListAdapter)
        fun setAdapterOnclickListener(adapter : NewsListAdapter)
        fun openDetailNews(link : String)
    }
    interface presenter{
        fun loadData(id : String)
        fun onDestroy()
        fun getFilterAdapter(query: String)
    }
    interface repository{
        fun jsonParser(message : String):  ArrayList<ArticlesItem>
    }
}