package com.example.androidappsearch

import androidx.appsearch.app.AppSearchSession
import kotlinx.coroutines.flow.Flow

interface NoteAppSearchManagerApi {
    fun initAppSearchFlow(documentClasses: Class<*>): Flow<AppSearchSession?>
}