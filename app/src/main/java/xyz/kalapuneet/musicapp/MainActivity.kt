package xyz.kalapuneet.musicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import xyz.kalapuneet.musicapp.ui.theme.MusicAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MusicViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicAppTheme {
                val uiState by viewModel.uiState.collectAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ExitUntilCollapsedLargeTopAppBar(uiState, viewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExitUntilCollapsedLargeTopAppBar(uiState: MusicTracksUiState, viewModel: MusicViewModel) {
    val scrollBehaviour = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Column (modifier = Modifier.nestedScroll(scrollBehaviour.nestedScrollConnection)) {
        LargeTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.title_discover),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            },
            scrollBehavior = scrollBehaviour
        )
        SearchBar(viewModel = viewModel, uiState = uiState)
        if (uiState.loading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else if (uiState.error != null) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Error: ${uiState.error}",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else if (uiState.tracks.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = stringResource(R.string.title_no_tracks),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            LazyVerticalGrid(
                modifier = Modifier.padding(16.dp),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val musicTracks = uiState.tracks
                items(musicTracks) {
                    SongCard(uiModel = it)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(viewModel: MusicViewModel, uiState: MusicTracksUiState) {
    var searchInput by remember { mutableStateOf("") }
    TextField(
        value = searchInput,
        modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
        onValueChange = { query ->
            searchInput = query
            viewModel.searchMusicTrack(query)
        },
        leadingIcon = {
            if (uiState.loading) {
                CircularProgressIndicator()
            } else {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier.height(24.dp).width(24.dp)
                )
            }
        }
    )
}
