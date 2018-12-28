package com.example.raka.testtoko.detailnews

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.raka.testtoko.R
import kotlinx.android.synthetic.main.activity_detail_news.*
import kotlinx.android.synthetic.main.activity_news_list.*

class DetailNewsActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var link: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_news)
        link = intent.getStringExtra("LINK")
        initButton()
    }
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btn_detail_close->closePage()
        }
    }
    private fun initButton(){
        btn_detail_close.setOnClickListener(this)
        wb_detail.loadUrl(link)
    }

    private fun closePage(){
        finish()
    }
}
