package com.example.project1.utilities.interfaces

import android.app.Activity
import android.content.Context
import kotlinx.coroutines.flow.StateFlow

interface iRunner {
    val result: StateFlow<Int?>
    val activity: Activity
    val context: Context
   suspend fun run(): Int
}