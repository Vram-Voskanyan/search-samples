package com.example.searchmanager.internal

import androidx.appsearch.app.AppSearchSession
import androidx.appsearch.app.SearchResult
import androidx.appsearch.app.SearchSpec
import androidx.concurrent.futures.await
import com.example.searchmanager.SearchManagerApi

internal class SearchManagerImpl(private val appSearchSession: AppSearchSession) :
    SearchManagerApi {
    override suspend fun queryNotes(query: String): List<SearchResult> {
        val searchSpec = SearchSpec.Builder()
            .setRankingStrategy(SearchSpec.RANKING_STRATEGY_CREATION_TIMESTAMP)
            .setSnippetCount(10)
            .build()

        val searchResults = appSearchSession.search(query, searchSpec)
        return searchResults.nextPageAsync.await()
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

