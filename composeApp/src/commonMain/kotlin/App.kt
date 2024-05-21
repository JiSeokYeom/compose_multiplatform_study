import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.HomeScreen
import ui.theme.darkScheme
import ui.theme.lightScheme

@OptIn(ExperimentalResourceApi::class)
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Composable
@Preview
fun App() {

    val colors = if (!isSystemInDarkTheme()) lightScheme else darkScheme
    /*  startKoin {
          printLogger() // Log Koin activity (optional)
          modules(appModule) // Load your Koin module
      }*/

    MaterialTheme(
        colorScheme = colors,
    ) {
        HomeScreen()
      /*  var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }*/
    }

}