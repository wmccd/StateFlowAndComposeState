package com.wmccd.presentinglayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wmccd.models.Album
import com.wmccd.models.Book
import com.wmccd.repositorylayer.MyRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class StateFlowVM( private val myRepository: MyRepository): ViewModel() {

    val simpleValueStateFlow: StateFlow<Int> = myRepository.simpleValue.stateIn(
        scope = viewModelScope,
        initialValue = 0,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
    )

    val counterStateFlow: StateFlow<Int> = myRepository.counter.stateIn(
        scope = viewModelScope,
        initialValue = 0,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
    )

    val albumsStateFlow: StateFlow<List<Album>> = myRepository.albums.stateIn(
        scope = viewModelScope,
        initialValue = listOf(),
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
    )

    val booksStateFlow: StateFlow<List<Book>> = myRepository.books.stateIn(
        scope = viewModelScope,
        initialValue = listOf(),
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
    )

    val nameCombinations: StateFlow<List<CombinedNames>> = combine(
        myRepository.books,
        myRepository.albums
    ){ books, albums ->
        val combines = arrayListOf<CombinedNames>()
        albums.forEach { album->
            books.forEach {book->
                combines.add( CombinedNames( "${album.name} | ${book.name}"))
            }
        }
        combines
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        initialValue = listOf<CombinedNames>()
    )

    fun incrementSimpleValue(){
        myRepository.incrementSimpleValue()
    }

}