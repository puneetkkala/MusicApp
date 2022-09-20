package xyz.kalapuneet.musicapp.data.network

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import xyz.kalapuneet.musicapp.data.model.Response

interface MusicService {
    @GET("v2/5df79a3a320000f0612e0115")
    fun getTracks(): Observable<Response>

    @GET("v2/5df79b1f320000f4612e011e")
    fun searchTracks(): Observable<Response>
}