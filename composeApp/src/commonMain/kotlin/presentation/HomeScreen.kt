package presentation

import HomeViewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import io.ktor.util.logging.KtorSimpleLogger

@Composable
fun HomeScreen(
) {
    val viewModel = remember { HomeViewModel() }

    val responseData by viewModel.events.collectAsState()

  /*  LaunchedEffect(key1 = responseData){
        when(responseData){
            RequestState.Idle -> KtorSimpleLogger("RequestState.Idle")
            RequestState.Loading -> TODO()
            is RequestState.Success -> TODO(
            is RequestState.Error -> TODO()

        }
    }*/



    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        KtorSimpleLogger("RequestState.Idle")
        Text(
            text = "test 화면 \n $responseData"
        )
    }
}