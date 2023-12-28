package app.rvlabs.rickmortyexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.rvlabs.SingleCharacterQuery
import app.rvlabs.rickmortyexplorer.ui.theme.RickMortyExplorerTheme
import com.apollographql.apollo3.ApolloClient
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickMortyExplorerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val apolloClient = ApolloClient.Builder()
                        .serverUrl("https://rickandmortyapi.com/graphql")
                        .build()

                    runBlocking {
                        launch {
                            val response = apolloClient.query(SingleCharacterQuery(character_id = "2")).execute()
                            println("Character.name=${response.data?.character?.name}")
                        }
                    }
                }
            }
        }
    }
}