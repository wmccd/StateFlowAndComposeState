package com.wmccd.states

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wmccd.presentinglayer.ComposeStateVM
import com.wmccd.presentinglayer.StateFlowVM
import com.wmccd.repositorylayer.MyRepository
import com.wmccd.datasourcelayer.SimpleDataStore
import com.wmccd.states.ui.theme.StatesTheme
import com.wmccd.uilayer.ComposeStateScreen
import com.wmccd.uilayer.StateFlowScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val simpleDataStore = SimpleDataStore(this)
        val myRepository = MyRepository(simpleDataStore)
        val stateFlowVM = StateFlowVM(myRepository)
        val composeStateVM = ComposeStateVM(myRepository)

        setContent {
            StatesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ){
                        StateFlowScreen(viewModel = stateFlowVM)
                        Spacer(modifier = Modifier.height(8.dp))
                        ComposeStateScreen(viewModel = composeStateVM)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StatesTheme {
        Greeting("Android")
    }
}