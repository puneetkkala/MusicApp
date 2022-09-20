package xyz.kalapuneet.musicapp.data.repo

import io.reactivex.rxjava3.core.Observable
import xyz.kalapuneet.musicapp.data.model.MusicTrack
import xyz.kalapuneet.musicapp.data.network.MusicDataSource

class MusicRepository {
    fun getTracks(): Observable<List<MusicTrack>> {
        return MusicDataSource().getTracks().map { items ->
            MusicTransformer.transformTracks(items)
        }
    }

    fun searchTracks(query: String): Observable<List<MusicTrack>> {
        val searchQuery = query.lowercase()
        return MusicDataSource().searchTracks().map { items ->
            MusicTransformer
                .transformTracks(items)
                .filter { track ->
                    track.title.lowercase().contains(searchQuery) || track.genres.any { genre ->
                        genre.lowercase().contains(searchQuery)
                    }
                }
        }
    }
}
