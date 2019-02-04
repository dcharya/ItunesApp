package com.example.rssfeeder.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rssfeeder.services.model.StackApiResponse
import com.example.rssfeeder.util.ActionListener
import io.reactivex.disposables.CompositeDisposable

class StackOverFlowViewModel : ViewModel() {
    var actionListener: ActionListener? = null
    lateinit var context: Context

    var stackApiResponse = MutableLiveData<StackApiResponse>()

    private val compositeDisposable = CompositeDisposable()

}
