package com.example.project1.utilities.dataclasses

import android.app.Activity
import android.content.ContentResolver
import android.content.Context

data class ContextData(
    val activity: Activity?,
    val contentResolver: ContentResolver?,
    val context: Context?
)
