package com.wmccd.datasourcelayer

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SimpleDataStore(private val context: Context)
{

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("SimpleStore")
        private val SIMPLE_VALUE = intPreferencesKey("SIMPLE_VALUE")
    }

    val fetchSimpleValue: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[SIMPLE_VALUE] ?: 0
    }

    suspend fun saveSimpleValue(value: Int) {
        context.dataStore.edit { preferences ->
            preferences[SIMPLE_VALUE] = value
        }
    }

}


