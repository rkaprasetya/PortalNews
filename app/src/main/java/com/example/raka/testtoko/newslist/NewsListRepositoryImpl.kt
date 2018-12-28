package com.example.raka.testtoko.newslist

import android.content.Context
import android.util.Log
import com.example.raka.testtoko.model.ArticlesItem
import org.json.JSONObject

class NewsListRepositoryImpl(val context: Context): NewsListContracts.repository{
    override fun jsonParser(response: String): ArrayList<ArticlesItem> {
        val listNews = ArrayList<ArticlesItem>()
        try {

            val jsonObject = JSONObject(response)
            val status = jsonObject.getString("status")
            if (status == "ok") {
                val jsonArray = jsonObject.getJSONArray("articles")
                for (i in 0 until jsonArray.length()) {
                    val jsonObject1 = jsonArray.getJSONObject(i)
                    val title = jsonObject1.getString("title")
                    val author = jsonObject1.getString("author")
                    val description = jsonObject1.getString("description")
                    val url = jsonObject1.getString("url")
                    val urlImage = jsonObject1.getString("urlToImage")
                    val date = jsonObject1.getString("publishedAt")
                    val content = jsonObject1.getString("content")
                    val article = ArticlesItem()
                    article.title = title
                    article.author = author
                    article.content = content
                    article.description = description
                    article.publishedAt = date
                    article.url = url
                    article.urlToImage = urlImage
                    listNews.add(article)
                }

            }else{
            }
        }catch (e: Exception){
            Log.e("JSONException", e.message)
        }
        return listNews

    }

}