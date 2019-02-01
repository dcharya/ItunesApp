package com.example.rssfeeder.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rssfeeder.services.model.SongList
import com.example.rssfeeder.util.ActionListener
import com.example.rssfeeder.views.home.fragments.HomeFragment
import com.example.rssfeeder.repository.ITunesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class HomeViewModel : ViewModel() {

    var actionListener: ActionListener? = null
    var songListData = MutableLiveData<SongList>()

    private val compositeDisposable = CompositeDisposable()

    fun getSongList(artist: String) {
        val disposable = ITunesRepository.initService().getTracks(artist)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Response<SongList>>() {
                override fun onComplete() {

                }

                override fun onNext(response: Response<SongList>) {
                    songListData.value = response.body()
                }

                override fun onError(e: Throwable) {
                    actionListener?.onAction(HomeFragment.ACTION_SHOW_ERROR, null)
                }

            })
        compositeDisposable.add(disposable)
    }


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}

