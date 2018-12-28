package com.example.raka.testtoko.newslist

import android.util.Log
import android.view.View
import com.example.raka.testtoko.model.ArticlesItem
import com.example.raka.testtoko.network.ApiClient
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

class NewsListPresenterImpl(var view: NewsListContracts.view?, val repository: NewsListContracts.repository) : NewsListContracts.presenter {
    lateinit var disposable: Disposable
    lateinit var call: Observable<ResponseBody>
    lateinit var adapter: NewsListAdapter
    override fun onDestroy() {
        view = null
    }

    override fun loadData(id: String) {
        view!!.showProgressBar()

        if (id == "ABC News") {
            call = ApiClient().apiService.getAbcNews()
        } else if (id == "The Wall Street Journal") {
            call = ApiClient().apiService.getWsj()
        } else if(id == "BBC News"){
            call = ApiClient().apiService.getBbc()
        }
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ResponseBody> {
                    override fun onComplete() {
                        view!!.dismissProgessBar()
                    }

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onNext(t: ResponseBody) {
                        val response = t.string()
                        val listNews = repository.jsonParser(response)
                        setAdapter(listNews)
                    }

                    override fun onError(e: Throwable) {
                        Log.e("", "Error Found:${e.message}")
                    }

                })

    }
    override fun getFilterAdapter(query: String) {
        adapter.filter.filter(query)
        adapter.notifyDataSetChanged()
    }
    private fun setAdapter(list: List<ArticlesItem>) {
        adapter = NewsListAdapter(list)
        adapter.setOnItemClickListener(object : NewsListAdapter.OnItemClickListener{
            override fun onClick(v: View, articlesItem: ArticlesItem, position : Int) {
               view!!.openDetailNews(articlesItem.url!!)
            }

        })
        view!!.setAdapter(adapter)
        adapter.notifyDataSetChanged()
    }

}