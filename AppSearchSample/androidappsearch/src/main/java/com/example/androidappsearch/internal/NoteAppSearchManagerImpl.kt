/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androidappsearch.internal

import android.content.Context
import androidx.appsearch.app.SetSchemaRequest
import androidx.appsearch.localstorage.LocalStorage
import androidx.appsearch.platformstorage.PlatformStorage
import androidx.concurrent.futures.await
import androidx.core.os.BuildCompat
import com.example.androidappsearch.NoteAppSearchManagerApi
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.flow

/**
 * [NoteAppSearchManagerImpl] is responsible only for the creation of the "appSearchSession"
 */
internal class NoteAppSearchManagerImpl(private val context: Context): NoteAppSearchManagerApi {

  override fun initAppSearchFlow(documentClasses: Class<*>) = flow {
      emit(null)
      // Creates a [AppSearchSession], for S+ devices uses PlatformStorage, for R- devices uses
      // LocalStorage session.
      val appSearchSession = if (BuildCompat.isAtLeastS()) {
        PlatformStorage.createSearchSessionAsync(
          PlatformStorage.SearchContext.Builder(context, DATABASE_NAME).build()
        ).await()
      } else {
        LocalStorage.createSearchSessionAsync(
          LocalStorage.SearchContext.Builder(context, DATABASE_NAME).build()
        ).await()
      }
      try {
        // Sets the schema for the AppSearch database by registering the [Note] document class as a
        // schema type in the overall database schema.
        val setSchemaRequest =
          SetSchemaRequest.Builder().addDocumentClasses(documentClasses).build()
        appSearchSession.setSchemaAsync(setSchemaRequest).await()

        // Set the [NoteAppSearchManager] instance as initialized to allow AppSearch operations to
        // be called.
        emit(appSearchSession)

        awaitCancellation()
      } finally {
        appSearchSession.close()
      }
  }

  companion object {
    private const val DATABASE_NAME = "notesDatabase"
  }
}
