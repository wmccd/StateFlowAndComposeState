package com.wmccd.presentinglayer

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wmccd.repositorylayer.Album
import com.wmccd.repositorylayer.Book
import com.wmccd.repositorylayer.MyRepository
import kotlinx.coroutines.launch

class ComposeStateVM( private val myRepository: MyRepository): ViewModel() {

    var simpleValueComposeState by mutableStateOf(0)
        private set
    private val simpleValueObserver = viewModelScope.launch {
        Log.d("XXX", "Inside the Compose Simple launch")

        myRepository.simpleValue.collect{
            Log.d("XXX", "Inside the Compose Simple collect")

            simpleValueComposeState = it
        }
    }

    var counterComposeState by mutableStateOf(0)
        private set
    private val counterObserver = viewModelScope.launch {
        myRepository.counter.collect{
            Log.d("ZZZ", "setting counter $it")
            counterComposeState = it
        }
    }

    var albumsComposeState by mutableStateOf(listOf<Album>())
        private set
    private val albumsObserver = viewModelScope.launch {
        myRepository.albums.collect{
            albumsComposeState = it
        }
    }

    var booksComposeState by mutableStateOf(listOf<Book>())
        private set
    private val booksObserver = viewModelScope.launch {
        myRepository.books.collect{
            booksComposeState = it
        }
    }

    var nameCombinations by mutableStateOf(listOf<CombinedNames>())
        private set
    private val combinedObserver = viewModelScope.launch {
        val combines = arrayListOf<CombinedNames>()
        albumsComposeState.forEach { album->
                booksComposeState.forEach {book->
                    combines.add( CombinedNames( "${album.name} | ${book.name}"))
                }
            }
            nameCombinations = combines
        }

    fun incrementSimpleValue(){
        myRepository.incrementSimpleValue()
    }
}