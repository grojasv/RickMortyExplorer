package app.rvlabs.rickmortyexplorer.presentation.characterdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.rvlabs.rickmortyexplorer.R
import app.rvlabs.rickmortyexplorer.domain.model.CharacterDetailsModel
import app.rvlabs.rickmortyexplorer.presentation.ui.common.CharacterNameTitle
import app.rvlabs.rickmortyexplorer.presentation.ui.common.TextIconComponent
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsScreen(
    state: CharacterDetailsViewModel.CharacterDetailsState,
    onFavoriteClicked: (characterId: String) -> Unit,
    onBackArrowClicked: () -> Unit

) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Column {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { onBackArrowClicked() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "ArrowBack icon"
                        )
                    }
                },
                title = { Text(text = state.character.name) },
                modifier = Modifier.shadow(elevation = 5.dp)
            )
            DetailsSection(character = state.character, onFavoriteClicked = onFavoriteClicked)
        }
    }
}

@Composable
fun DetailsSection(
    character: CharacterDetailsModel, onFavoriteClicked: (characterId: String) -> Unit
) {
    Box(
        modifier = Modifier.padding(
            top = 150.dp, bottom = 16.dp, start = 16.dp, end = 16.dp
        )
    ) {
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ), modifier = Modifier.shadow(8.dp, RoundedCornerShape(10.dp))
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 24.dp, bottom = 16.dp, start = 16.dp, end = 16.dp
                    ), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CharacterNameTitle(text = character.name)
                Spacer(modifier = Modifier.height(16.dp))
                TextIconComponent(text = character.origin, imageVector = Icons.Default.Place)
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextIconComponent(text = character.gender, imageVector = Icons.Default.Person)
                    TextIconComponent(text = character.species, imageVector = Icons.Default.Face)
                    TextIconComponent(
                        text = character.status, imageVector = Icons.Default.CheckCircle
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Box(
                    contentAlignment = Alignment.BottomEnd, modifier = Modifier.fillMaxWidth()
                ) {
                    ElevatedButton(modifier = Modifier.padding(vertical = 2.dp, horizontal = 12.dp),
                        onClick = { onFavoriteClicked(character.id) }) {
                        Image(
                            painter = painterResource(id = R.drawable.img_favorite),
                            contentDescription = "Favorite",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = stringResource(R.string.mark_favorite).uppercase()
                        )
                    }
                }
            }
        }
        AsyncImage(
            model = character.image,
            contentDescription = "${character.name} avatar",
            modifier = Modifier
                .size(120.dp)
                .offset(y = (-100).dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                .align(alignment = Alignment.TopCenter)
        )
    }
}