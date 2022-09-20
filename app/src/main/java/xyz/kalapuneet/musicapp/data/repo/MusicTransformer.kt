package xyz.kalapuneet.musicapp.data.repo

import xyz.kalapuneet.musicapp.data.model.MusicTrack
import xyz.kalapuneet.musicapp.data.model.SessionsItem

object MusicTransformer {

    fun transformTracks(items: List<SessionsItem?>): List<MusicTrack> {
        val musicTracks = mutableListOf<MusicTrack>()
        items.filterNotNull().forEach {
            val listenerCount = it.listenerCount
            val title = it.currentTrack?.title
            val genres = it.genres?.filterNotNull()
            val artworkUrl = it.currentTrack?.artworkUrl
            if (listenerCount != null && title != null && genres != null && artworkUrl != null) {
               musicTracks.add(MusicTrack(listenerCount, artworkUrl, title, genres))
            }
        }
        return musicTracks
    }
}