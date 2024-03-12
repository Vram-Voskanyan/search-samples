package com.example.notemanager.internal

import androidx.appsearch.app.AppSearchBatchResult
import androidx.appsearch.app.AppSearchSession
import androidx.appsearch.app.PutDocumentsRequest
import androidx.appsearch.app.RemoveByDocumentIdRequest
import androidx.appsearch.app.SearchResult
import androidx.appsearch.app.SearchSpec
import androidx.concurrent.futures.await
import com.example.notemanager.model.Note
import com.example.notemanager.NoteManagerApi
import java.util.UUID

internal class NoteManagerImpl(private val appSearchSession: AppSearchSession): NoteManagerApi {
    override suspend fun addNote(text: String, title: String): AppSearchBatchResult<String, Void> {
        val id = UUID.randomUUID().toString()
        val note = Note(id = id, text = text, title = title)
        val request = PutDocumentsRequest.Builder().addDocuments(note).build()
        return appSearchSession.putAsync(request).await()
    }

    override suspend fun removeNote(
        namespace: String,
        id: String
    ): AppSearchBatchResult<String, Void> {
        val request =
            RemoveByDocumentIdRequest.Builder(namespace).addIds(id).build()
        return appSearchSession.removeAsync(request).await()
    }

    override suspend fun queryLatestNotes(snippetCount: Int): List<SearchResult> {
        val searchSpec = SearchSpec.Builder()
            .setRankingStrategy(SearchSpec.RANKING_STRATEGY_CREATION_TIMESTAMP)
            .setSnippetCount(snippetCount)
            .build()

        val searchResults = appSearchSession.search("", searchSpec)
        return searchResults.nextPageAsync.await()
    }

}