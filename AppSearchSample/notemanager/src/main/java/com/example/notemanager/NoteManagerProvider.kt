package com.example.notemanager

import androidx.appsearch.app.AppSearchSession
import com.example.notemanager.internal.NoteManagerImpl

fun provideNoteManager(appSearchSession: AppSearchSession): NoteManagerApi = NoteManagerImpl(appSearchSession)