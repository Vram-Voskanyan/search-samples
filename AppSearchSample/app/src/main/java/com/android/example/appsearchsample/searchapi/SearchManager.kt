package com.android.example.appsearchsample.searchapi

import androidx.appsearch.app.AppSearchSession
import androidx.appsearch.app.SearchResult
import androidx.appsearch.app.SearchSpec
import androidx.concurrent.futures.await
import com.android.example.appsearchsample.model.Note

interface SearchManagerApi {

    /**
     * Queries the AppSearch database for matching [Note] documents.
     *
     * @return a list of [SearchResult] objects. This returns SearchResults in the order
     * they were created (with most recent first). This returns a maximum of 10
     * SearchResults that match the query, per AppSearch default page size.
     * Snippets are returned for the first 10 results.
     */
    suspend fun queryNotes(query: String): List<SearchResult>
}

internal class SearchManagerImpl(private val appSearchSession: AppSearchSession): SearchManagerApi {
    override suspend fun queryNotes(query: String): List<SearchResult>  {
        val searchSpec = SearchSpec.Builder()
            .setRankingStrategy(SearchSpec.RANKING_STRATEGY_CREATION_TIMESTAMP)
            .setSnippetCount(10)
            .build()

        val searchResults = appSearchSession.search(query, searchSpec)
        return searchResults.nextPageAsync.await()
    }

}