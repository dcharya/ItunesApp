package com.example.rssfeeder.services

import com.example.rssfeeder.services.model.StackApiResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StackOverFlowAPI {

    @GET("answers")
    fun getAnswers(
        @Query("page") page: Int,
        @Query("pagesize") pagesize: Int,
        @Query("site") site: String
    ): Single<Response<StackApiResponse>>
}