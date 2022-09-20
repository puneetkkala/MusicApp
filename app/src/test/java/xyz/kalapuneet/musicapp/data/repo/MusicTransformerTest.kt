package xyz.kalapuneet.musicapp.data.repo

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import xyz.kalapuneet.musicapp.data.model.CurrentTrack
import xyz.kalapuneet.musicapp.data.model.MusicTrack
import xyz.kalapuneet.musicapp.data.model.SessionsItem

@RunWith(JUnit4::class)
class MusicTransformerTest {

    @Test
    fun test_whenContentItemsPresent_thenMusicTracksReturned() {
        val sessionsItem = listOf(SessionsItem(
            CurrentTrack(
                "dummy",
                title = "dummy"
            ),
            genres = listOf("dummy"),
            name = "",
            listenerCount = 14
        ))
        val actualTracks = MusicTransformer.transformTracks(sessionsItem)
        val expectedTracks = listOf(MusicTrack(
            listenerCount = 14,
            artworkUrl = "dummy",
            title = "dummy",
            genres = listOf("dummy")
        ))
        assertEquals(expectedTracks[0], actualTracks[0])
    }
}