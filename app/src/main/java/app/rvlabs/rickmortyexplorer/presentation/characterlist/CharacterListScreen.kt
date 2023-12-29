package app.rvlabs.rickmortyexplorer.presentation.characterlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.rvlabs.rickmortyexplorer.R
import app.rvlabs.rickmortyexplorer.core.Constants.FILTER_NONE
import app.rvlabs.rickmortyexplorer.domain.model.CharacterOverviewModel
import app.rvlabs.rickmortyexplorer.presentation.ui.common.CharacterNameTitle
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    state: CharacterListViewModel.CharacterListState,
    onCharacterClicked: (characterId: String) -> Unit,
    onFilterActionClicked: () -> Unit

) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopAppBar(
                actions = {
                    IconButton(onClick = { onFilterActionClicked() }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_filter_list_24),
                            contentDescription = "Filter icon"
                        )
                    }
                },
                title = { Text(text = stringResource(R.string.character_list_screen_title)) },
                modifier = Modifier.shadow(elevation = 5.dp)
            )
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
    Column {
        if (!state.currentFilter.equals(FILTER_NONE, ignoreCase = true)) {
            Text(
                text = stringResource(
                    R.string.filtering_by_gender,
                    state.currentFilter.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
                ),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

        LazyColumn {
            items(state.characters) { character ->
                CharacterItem(character = character,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .shadow(8.dp, RoundedCornerShape(8.dp))
                        .clickable { onCharacterClicked(character.id) })
            }
        }
    }
}

@Composable
fun CharacterItem(
    character: CharacterOverviewModel, modifier: Modifier = Modifier
) {
    OutlinedCard(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = "${character.name} avatar",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(0.8f),
            ) {
                CharacterNameTitle(text = character.name)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${character.gender} · ${character.origin}",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            if (character.favorite) {
                Image(
                    painter = painterResource(id = R.drawable.img_favorite),
                    contentDescription = "Favorite",
                    modifier = Modifier.size(32.dp)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.img_not_favorite),
                    contentDescription = "Not favorite",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}