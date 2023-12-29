package app.rvlabs.rickmortyexplorer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import app.rvlabs.rickmortyexplorer.core.Constants.PARAM_CHARACTER_ID
import app.rvlabs.rickmortyexplorer.presentation.characterdetails.CharacterDetailsScreen
import app.rvlabs.rickmortyexplorer.presentation.characterdetails.CharacterDetailsViewModel
import app.rvlabs.rickmortyexplorer.presentation.characterlist.CharacterListScreen
import app.rvlabs.rickmortyexplorer.presentation.characterlist.CharacterListViewModel
import app.rvlabs.rickmortyexplorer.presentation.ui.theme.RickMortyExplorerTheme
import dagger.hilt.android.AndroidEntryPoint

enum class AppScreen {
    CharacterList, CharacterDetails
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickMortyExplorerTheme {
                RickMortyApp()
            }
        }
    }
}

@Composable
fun RickMortyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreen.CharacterList.name) {
        composable(route = AppScreen.CharacterList.name) {
            val viewModel = hiltViewModel<CharacterListViewModel>()
            val state by viewModel.state.collectAsState()
            CharacterListScreen(
                state = state,
                onCharacterClicked = { characterId ->
                    navController.navigate("${AppScreen.CharacterDetails.name}/$characterId")
                },
                onFilterActionClicked = {
                    viewModel.applyCharacterFiltering()
                }
            )
        }
        composable(
            route = "${AppScreen.CharacterDetails.name}/{$PARAM_CHARACTER_ID}",
            arguments = listOf(
                navArgument(name = PARAM_CHARACTER_ID) {
                    type = NavType.StringType
                }
            )
        ) {
            val viewModel = hiltViewModel<CharacterDetailsViewModel>()
            val state by viewModel.state.collectAsState()
            val characterId = remember { it.arguments?.getString(PARAM_CHARACTER_ID) ?: "" }

            viewModel.loadCharacter(characterId)

            CharacterDetailsScreen(
                state = state,
                onFavoriteClicked = {
                    println("Favorite $it")
                },
                onBackArrowClicked = { navController.popBackStack() }
            )
        }
    }
}