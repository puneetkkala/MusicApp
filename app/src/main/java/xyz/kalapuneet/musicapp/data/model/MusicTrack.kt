package xyz.kalapuneet.musicapp.data.model

data class MusicTrack(
    val listenerCount: Int,
    val artworkUrl: String,
    val title: String,
    val genres: List<String>
)
