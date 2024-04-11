package com.example.notemanager

import androidx.appsearch.app.AppSearchBatchResult
import androidx.appsearch.app.SearchResult

interface NoteManagerApi {

    /**
     * Adds a [Note] document to the AppSearch database.
     */
    suspend fun addNote(text: String, title: String): AppSearchBatchResult<String, Void>

    /**
     * Removes [Note] document from the AppSearch database by namespace and
     * id.
     */
    suspend fun removeNote(
        namespace: String,
        id: String
    ): AppSearchBatchResult<String, Void>

}
