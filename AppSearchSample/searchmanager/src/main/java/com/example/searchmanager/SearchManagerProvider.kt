package com.example.searchmanager

import androidx.appsearch.app.AppSearchSession
import com.example.searchmanager.internal.SearchManagerImpl

fun provideSearchManager(appSearchSession: AppSearchSession): SearchManagerApi =
    SearchManagerImpl(appSearchSession)