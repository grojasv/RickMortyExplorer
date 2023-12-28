package app.rvlabs.rickmortyexplorer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import app.rvlabs.rickmortyexplorer.presentation.characterlist.CharacterListScreen
import app.rvlabs.rickmortyexplorer.presentation.characterlist.CharacterListViewModel
import app.rvlabs.rickmortyexplorer.presentation.ui.theme.RickMortyExplorerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickMortyExplorerTheme {

                val viewModel = hiltViewModel<CharacterListViewModel>()
                val state by viewModel.state.collectAsState()

                CharacterListScreen(
                    state = state,
                    onCharacterClicked = { characterId -> println("CharacterClicked $characterId") }
                )
//                CharacterDetailsScreen()

            }
        }
    }
}