package com.example.rssfeeder.repository

import com.example.rssfeeder.services.StackOverFlowAPI
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object StackOverFlowRepository {
    private const val HTTPS_STACK_API = "https://api.stackexchange.com/2.2/"
    fun initService(): StackOverFlowAPI {
        val retrofit = Retrofit.Builder().baseUrl(HTTPS_STACK_API)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(StackOverFlowAPI::class.java)
    }
}