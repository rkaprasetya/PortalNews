package com.example.raka.testtoko

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.raka.testtoko.newslist.NewsListActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initButtons()
    }
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btn_abcnews -> openListActivity("ABC News")
            R.id.btn_wsj -> openListActivity("The Wall Street Journal")
            R.id.btn_bbc -> openListActivity("BBC News")
        }
    }
    private fun openListActivity(message : String){
        val intent = Intent(this, NewsListActivity::class.java)
        intent.putExtra("ID",message)
        startActivity(intent)
    }

    private fun initButtons(){
        btn_abcnews.setOnClickListener(this)
        btn_wsj.setOnClickListener(this)
        btn_bbc.setOnClickListener(this)
    }

}
