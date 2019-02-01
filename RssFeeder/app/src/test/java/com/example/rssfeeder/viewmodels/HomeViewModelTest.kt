package com.example.rssfeeder.viewmodels

import androidx.lifecycle.Observer
import com.example.rssfeeder.repository.ITunesService
import com.example.rssfeeder.services.model.SongList
import com.example.rssfeeder.utils.JsonProvider
import com.example.rssfeeder.utils.RxImmediateSchedulerRule
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Response


@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [19])
class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel

    lateinit var iTunesService: ITunesService

    @Mock
    lateinit var observer: Observer<SongList>
    lateinit var gson: Gson

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        MockitoAnnotations.initMocks(this)
        homeViewModel = Mockito.spy(HomeViewModel::class.java)
        iTunesService = Mockito.spy(ITunesService::class.java)
        gson = Mockito.spy(Gson::class.java)

    }

    @Test
    fun testLiveData() {
        val artist = "Sonu"

        val songList = gson.fromJson<SongList>(JsonProvider.getJson(ARTIST_DATA_SONU), SongList::class.java)

        Mockito.`when`(iTunesService.getTracks(artist)).thenReturn(Observable.just(Response.success(songList)))
        homeViewModel.songListData.observeForever(observer)
        homeViewModel.getSongList(artist)
        print(homeViewModel.songListData)
    }

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
        const val ARTIST_DATA_SONU = "/valid/ARTIST_SONU_DATA.json"
    }
}