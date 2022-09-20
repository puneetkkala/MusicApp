package xyz.kalapuneet.musicapp

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import xyz.kalapuneet.musicapp.data.repo.MusicRepository

class MusicViewModel(private val repository: MusicRepository = MusicRepository()) : ViewModel() {

    private val _uiState = MutableStateFlow(MusicTracksUiState(loading = true))
    val uiState: StateFlow<MusicTracksUiState> = _uiState

    private val disposeBag = CompositeDisposable()

    init {
        getMusicTracks()
    }

    private fun getMusicTracks() {
        val disposable = repository
            .getTracks()
            .subscribeOn(Schedulers.io())
            .subscribe({
               _uiState.value = MusicTracksUiState(tracks = it)
            }, {
                _uiState.value = MusicTracksUiState(error = it.message)
            })
        disposeBag.add(disposable)
    }

    fun searchMusicTrack(query: String) {
        _uiState.value = MusicTracksUiState(loading = true)
        val disposable = repository
            .searchTracks(query)
            .subscribeOn(Schedulers.io())
            .subscribe({
               _uiState.value = MusicTracksUiState(it)
            }, {
                _uiState.value = MusicTracksUiState(error = it.message)
            })
        disposeBag.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.dispose()
    }
}