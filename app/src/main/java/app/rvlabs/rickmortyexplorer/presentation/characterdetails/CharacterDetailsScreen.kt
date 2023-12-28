package app.rvlabs.rickmortyexplorer.presentation.characterdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.rvlabs.rickmortyexplorer.R
import app.rvlabs.rickmortyexplorer.domain.model.CharacterDetailsModel

@Composable
fun CharacterDetailsScreen(
    state: CharacterDetailsViewModel.CharacterDetailsState,
    onFavoriteClicked: (characterId: String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        DetailsSection(character = state.character, onFavoriteClicked = onFavoriteClicked)
    }
}

@Composable
fun DetailsSection(
    character: CharacterDetailsModel, onFavoriteClicked: (characterId: String) -> Unit
) {
    Column {
        Text(text = character.name, style = MaterialTheme.typography.titleLarge)
        Text(text = character.gender, style = MaterialTheme.typography.bodyMedium)
        Text(text = character.origin, style = MaterialTheme.typography.bodyMedium)
        Text(text = character.image, style = MaterialTheme.typography.bodyMedium)
        Text(text = character.favorite.toString(), style = MaterialTheme.typography.bodyMedium)
        Text(text = character.species, style = MaterialTheme.typography.bodyMedium)
        Text(text = character.status, style = MaterialTheme.typography.bodyMedium)
        Button(onClick = { onFavoriteClicked(character.id) }) {
            Text(text = stringResource(R.string.mark_favorite))
        }
    }
}