package com.example.rssfeeder.repository

import com.example.rssfeeder.services.model.SongList
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesService {

    @GET("api/search/song/netease")
    fun getTracks(
        @Query("key") name: String,
        @Query("limit") limit: Int = 10, @Query("page") page: Int = 1
    ): Observable<Response<SongList>>
}