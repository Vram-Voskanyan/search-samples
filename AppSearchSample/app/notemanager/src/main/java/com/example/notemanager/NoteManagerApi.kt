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

    /**
     * Queries the AppSearch database for latest [Note] documents.
     *
     * @return a list of [SearchResult] objects. This returns SearchResults in the order
     * they were created (with most recent first). This returns a maximum of 10
     * SearchResults that match the query, per AppSearch default page size.
     * Snippets are returned for the first snippetCount results.
     */
    suspend fun queryLatestNotes(snippetCount: Int): List<SearchResult>
}
