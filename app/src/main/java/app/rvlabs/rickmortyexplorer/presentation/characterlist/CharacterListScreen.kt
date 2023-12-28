package app.rvlabs.rickmortyexplorer.presentation.characterlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.rvlabs.rickmortyexplorer.R
import app.rvlabs.rickmortyexplorer.domain.model.CharacterOverviewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    state: CharacterListViewModel.CharacterListState,
    onCharacterClicked: (characterId: String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopAppBar(title = { Text(text = stringResource(R.string.character_list_screen_title)) })
            if (state.isLoading) {
                LinearProgressIndicator()
            } else {
                ListSection(state = state, onCharacterClicked = onCharacterClicked)
            }
        }
    }
}

@Composable
fun ListSection(
    state: CharacterListViewModel.CharacterListState,
    onCharacterClicked: (characterId: String) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.characters) { character ->
            CharacterItem(
                character = character,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCharacterClicked(character.id) }
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun CharacterItem(
    character: CharacterOverviewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = character.name, style = MaterialTheme.typography.titleLarge)
        Text(text = character.gender, style = MaterialTheme.typography.bodyMedium)
        Text(text = character.origin, style = MaterialTheme.typography.bodyMedium)
        Text(text = character.image, style = MaterialTheme.typography.bodyMedium)
        Text(text = character.favorite.toString(), style = MaterialTheme.typography.bodyMedium)
    }
}