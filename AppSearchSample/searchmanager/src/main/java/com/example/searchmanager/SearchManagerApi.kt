package com.example.searchmanager

import androidx.appsearch.app.SearchResult

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