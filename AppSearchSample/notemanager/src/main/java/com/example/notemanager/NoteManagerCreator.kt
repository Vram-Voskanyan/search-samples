package com.example.notemanager

import androidx.appsearch.app.AppSearchSession
import com.example.notemanager.internal.NoteManagerImpl

fun createNoteManager(appSearchSession: AppSearchSession): NoteManagerApi = NoteManagerImpl(appSearchSession)