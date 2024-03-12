package com.example.searchmanager

import androidx.appsearch.app.AppSearchSession
import com.example.searchmanager.internal.SearchManagerImpl

fun createSearchManager(appSearchSession: AppSearchSession): SearchManagerApi =
    SearchManagerImpl(appSearchSession)