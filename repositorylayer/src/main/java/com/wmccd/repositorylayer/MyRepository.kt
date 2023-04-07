package com.wmccd.repositorylayer

import android.util.Log
import com.wmccd.datasourcelayer.SimpleDataStore
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class Album (
    val name : String = "",
    val act : String = "",
    val releaseYear : Int
)

data class Book (
    val name : String = "",
    val author : String = "",
    val releaseYear : Int
)

class MyRepository( private val simpleDataStore: SimpleDataStore) {

    private val albumList = arrayListOf<Album>(
        Album("name1", "act1", 1999),
        Album("name2", "act2", 2000),
        Album("name3", "act3", 2001),
        Album("name4", "act4", 2002),
        Album("name5", "act5", 2003),
    )

    private val bookList = arrayListOf<Book>(
        Book("name1", "author1", 1999),
        Book("name2", "author1", 1800),
        Book("name3", "author1", 2001),
        Book("name4", "author1", 1900),
    )

    private var simpleInt = 5

    val counter = flow<Int> {
        var count = 0
        repeat(10){
            emit(count++)
            delay(100)
        }
    }

    val simpleValue: Flow<Int>
        get() = simpleDataStore.fetchSimpleValue

    val albums: Flow<List<Album>>
        get() = flow{
            emit(albumList)
        }

    val books: Flow<List<Book>>
        get() = flow{
            emit(bookList)
        }

    fun incrementSimpleValue() {
        CoroutineScope(Job()).launch(Dispatchers.IO) {
            simpleDataStore.fetchSimpleValue.collect {
                simpleDataStore.saveSimpleValue(it + 1)
                this.cancel()
            }
        }
    }

    fun addAlbum(){
        albumList.add(
            Album(
                name = "name ${System.currentTimeMillis()}",
                act = "act ${System.currentTimeMillis()}",
                releaseYear = (System.currentTimeMillis().toInt())
            )
        )
    }

}