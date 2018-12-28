package com.example.raka.testtoko.newslist

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.SearchView
import com.example.raka.testtoko.R
import com.example.raka.testtoko.detailnews.DetailNewsActivity
import kotlinx.android.synthetic.main.activity_news_list.*

class NewsListActivity : AppCompatActivity(), NewsListContracts.view, View.OnClickListener {


    override fun setAdapterOnclickListener(adapter: NewsListAdapter) {

    }

    val repository = NewsListRepositoryImpl(this)
    var presenter: NewsListPresenterImpl? = NewsListPresenterImpl(this, repository)
    lateinit var id: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        id = intent.getStringExtra("ID")
        initWidget()
        presenter!!.loadData(id)

    }

    override fun closePage() {
        finish()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_list_close -> closePage()
        }
    }
    override fun openDetailNews(link: String) {
        val intent = Intent(this, DetailNewsActivity::class.java)
        intent.putExtra("LINK",link)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter!!.onDestroy()
        presenter = null
    }

    override fun dismissProgessBar() {
        pb_news.visibility = View.GONE
    }

    override fun showProgressBar() {
        pb_news.visibility = View.VISIBLE
    }

    override fun setAdapter(adapter: NewsListAdapter) {
        rv_news.adapter = adapter
    }

    private fun initWidget() {
        btn_list_close.setOnClickListener(this)
        setTitle()
        rv_news.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        search_news.queryHint = "Search News.."
        val searchText = search_news.findViewById(
                search_news.context.resources.getIdentifier(
                        "android:id/search_src_text",
                        null,
                        null
                )
        ) as AutoCompleteTextView
        val size = 15
        searchText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size.toFloat())
        search_news.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                rv_news.recycledViewPool.clear()
                presenter!!.getFilterAdapter(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //adapter.filter.filter(newText)
                rv_news.recycledViewPool.clear()
                presenter!!.getFilterAdapter(newText!!)
                return false
            }

        })
    }

    private fun setTitle() {
        tv_list_title.text = id
    }
}
