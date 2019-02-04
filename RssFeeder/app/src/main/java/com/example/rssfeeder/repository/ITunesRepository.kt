package com.example.rssfeeder.repository

import com.example.rssfeeder.services.ITunesService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ITunesRepository {
    private val HTTPS_API_ITUNES_URL = "https://music-api-jwzcyzizya.now.sh/"

    fun initService(): ITunesService {
        val retrofit = Retrofit.Builder().baseUrl(HTTPS_API_ITUNES_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return  retrofit.create(ITunesService::class.java)
    }
}
