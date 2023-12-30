package app.rvlabs.rickmortyexplorer.presentation

import android.content.Intent
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
import androidx.navigation.navDeepLink
import app.rvlabs.rickmortyexplorer.core.Constants.DEEPLINK_SCHEME
import app.rvlabs.rickmortyexplorer.core.Constants.PARAM_CHARACTER_ID
import app.rvlabs.rickmortyexplorer.core.Constants.SCREEN_CHARACTER_DETAILS
import app.rvlabs.rickmortyexplorer.core.Constants.SCREEN_CHARACTER_LIST
import app.rvlabs.rickmortyexplorer.presentation.characterdetails.CharacterDetailsScreen
import app.rvlabs.rickmortyexplorer.presentation.characterdetails.CharacterDetailsViewModel
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
                RickMortyApp()
            }
        }
    }
}

@Composable
fun RickMortyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SCREEN_CHARACTER_LIST) {
        composable(route = SCREEN_CHARACTER_LIST) {
            val viewModel = hiltViewModel<CharacterListViewModel>()
            val state by viewModel.state.collectAsState()
            CharacterListScreen(
                state = state,
                onCharacterClicked = { characterId ->
                    navController.navigate("${SCREEN_CHARACTER_DETAILS}/$characterId")
                },
                onFilterActionClicked = {
                    viewModel.applyCharacterFiltering()
                }
            )
        }
        composable(
            deepLinks = listOf(navDeepLink {
                uriPattern =
                    "$DEEPLINK_SCHEME://${SCREEN_CHARACTER_DETAILS}/{$PARAM_CHARACTER_ID}"
                action = Intent.ACTION_VIEW
            }),
            route = "${SCREEN_CHARACTER_DETAILS}/{$PARAM_CHARACTER_ID}",
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
                onFavoriteToggleClicked = {
                    viewModel.favoriteToggleClicked()
                },
                onBackArrowClicked = { navController.popBackStack() }
            )
        }
    }
}