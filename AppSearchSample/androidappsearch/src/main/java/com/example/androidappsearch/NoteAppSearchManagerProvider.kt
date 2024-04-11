package com.example.androidappsearch

import android.content.Context
import com.example.androidappsearch.internal.NoteAppSearchManagerImpl

private var noteAppSearchManagerImpl: NoteAppSearchManagerApi? = null

fun provideLazyNoteAppSearchManager(context: Context): NoteAppSearchManagerApi =
  noteAppSearchManagerImpl
        ?: NoteAppSearchManagerImpl(context).also { noteAppSearchManagerImpl = it }
