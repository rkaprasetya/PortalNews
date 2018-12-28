package com.example.raka.testtoko.network

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET

interface ApiService {
    @GET("top-headlines?sources=abc-news&apiKey=331b477e62ab40deb6e4cf97a1800149")
    fun getAbcNews(): Observable<ResponseBody>

    @GET("everything?domains=wsj.com&apiKey=331b477e62ab40deb6e4cf97a1800149")
    fun getWsj(): Observable<ResponseBody>

    @GET("top-headlines?sources=bbc-news&apiKey=331b477e62ab40deb6e4cf97a1800149")
    fun getBbc(): Observable<ResponseBody>

}