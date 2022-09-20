package xyz.kalapuneet.musicapp.data.network

import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import xyz.kalapuneet.musicapp.data.model.SessionsItem

class MusicDataSource {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    private val service: MusicService
        get() = retrofit.create(MusicService::class.java)

    fun getTracks(): Observable<List<SessionsItem?>> {
        return service.getTracks().map { it.data?.sessions ?: listOf() }
    }

    fun searchTracks(): Observable<List<SessionsItem?>> {
        return service.searchTracks().map { it.data?.sessions ?: listOf() }
    }
}