package com.wmccd.uilayer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wmccd.presentinglayer.StateFlowVM

@Composable
fun StateFlowScreen( viewModel: StateFlowVM ){

    val counter = viewModel.counterStateFlow.collectAsStateWithLifecycle().value
    val simpleValue = viewModel.simpleValueStateFlow.collectAsStateWithLifecycle().value
    val albums = viewModel.albumsStateFlow.collectAsStateWithLifecycle().value
    val books = viewModel.booksStateFlow.collectAsStateWithLifecycle().value
    val combined = viewModel.nameCombinations.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Hello From StateFlow",
            style = MaterialTheme.typography.titleLarge
        )
        Text(text = "Simple Value: $simpleValue")
        Text(text = "Counter: $counter")
        Text(text = "Album Count: ${albums.size}")
        Text(text = "Book Count: ${books.size}")
        Text(text = "Combined Count: ${combined.size}")
        Button(onClick = {
            viewModel.incrementSimpleValue()
        }) {
            Text("Increment Simple Value")
        }

    }

}