package xyz.kalapuneet.musicapp

import xyz.kalapuneet.musicapp.data.model.MusicTrack

class MusicTracksUiState(
    val tracks: List<MusicTrack> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)