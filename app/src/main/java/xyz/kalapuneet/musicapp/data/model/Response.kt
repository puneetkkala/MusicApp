package xyz.kalapuneet.musicapp.data.model

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("data")
	val data: Data? = null
)

data class Data(

	@field:SerializedName("sessions")
	val sessions: List<SessionsItem?>? = null
)

data class SessionsItem(

	@field:SerializedName("current_track")
	val currentTrack: CurrentTrack? = null,

	@field:SerializedName("genres")
	val genres: List<String?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("listener_count")
	val listenerCount: Int? = null
)

data class CurrentTrack(

	@field:SerializedName("artwork_url")
	val artworkUrl: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)
