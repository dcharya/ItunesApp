package com.example.rssfeeder

import android.net.Uri
import androidx.lifecycle.*
import com.squareup.okhttp.mockwebserver.MockWebServer
import org.mockito.Mockito
import org.robolectric.annotation.Config
import java.util.concurrent.CountDownLatch

@Config(manifest = Config.NONE, sdk = [21])
open class BaseTest {

    val webServer by lazy { MockWebServer() }
    protected lateinit var baseuri: Uri
    protected var queryString = ""
    protected val mockLifecycleOwner = Mockito.mock(LifecycleOwner::class.java)
    //    protected val gson = Mockito.mock(Gson::class.java)
    open fun setup() {

        webServer.start()

        val url = webServer.url(BASE_URL_PATH).toString()
        baseuri = Uri.parse(url)
        Mockito.`when`(mockLifecycleOwner.lifecycle)
            .thenReturn(LifecycleRegistry(mockLifecycleOwner).apply { handleLifecycleEvent(Lifecycle.Event.ON_RESUME) })
    }

    fun <T> getLiveData(liveData: LiveData<T>?, liveDataApi: () -> Unit) {
        val countDownLatch = CountDownLatch(1)
        liveData?.observe(mockLifecycleOwner, Observer<T> {
            countDownLatch.countDown()
        })
        liveDataApi.invoke()
        countDownLatch.await()
    }

    companion object {
        const val BASE_URL_PATH = "https://api.apixu.com"
    }

}