package com.example.rssfeeder.repository

import androidx.paging.ItemKeyedDataSource
import androidx.paging.PageKeyedDataSource
import com.example.rssfeeder.services.model.Item
import com.example.rssfeeder.services.model.StackApiResponse
import io.reactivex.disposables.CompositeDisposable

class DataSource(val compositeDisposable: CompositeDisposable) : PageKeyedDataSource<Int, Item>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Item>) {
        val disposable = StackOverFlowRepository
            .initService().getAnswers(params.requestedLoadSize, PAGE_SIZE, SITE_NAME)
            .subscribe({ response ->
                var nextKey = if (params.requestedLoadSize>1) params.requestedLoadSize - 1
                else 0
                callback.onResult(


                    response.body()!!.items,
                    null,
                    nextKey
                )
            }, { t2: Throwable? -> })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        callback.onResult()
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        const val FIRST_PAGE = 1
        const val PAGE_SIZE = 20
        const val SITE_NAME = "stackoverflow"
    }

}