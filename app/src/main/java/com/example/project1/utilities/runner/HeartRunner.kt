package com.example.project1.utilities.runner

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.project1.utilities.calculator.HeartRateCalculator
import com.example.project1.utilities.dataclasses.ContextData
import com.example.project1.utilities.dataclasses.VideoMetaData
import com.example.project1.utilities.interfaces.iRunner
import com.example.project1.utilities.reader.ReadMedia
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class HeartRunner(override val activity: Activity, override val context: Context): iRunner {
    private val _heartResult = MutableStateFlow<Int?>(null)
    override var result: StateFlow<Int?> = _heartResult.asStateFlow()
    @RequiresApi(Build.VERSION_CODES.P)
    override suspend fun run(): Int {
        return withContext(Dispatchers.IO){
            Log.d("heart", "run: ")
            val contentResolver = context.contentResolver
            Log.d("heart", "contentResolver: $contentResolver")
            // create VideoMetaData
            val videoMeta = VideoMetaData(
                videoName = "HeartRate.mp4"
            )
            Log.d("heart", "videoMeta: $videoMeta")
            // call ReadMedia
            val contextData = ContextData(
                activity = activity,
                contentResolver = contentResolver,
                context = null
            )
            Log.d("heart", "contextData: $contextData")

            val heartSignsData = ReadMedia(videoMeta).read(contextData)
            Log.d("heart", "heartSignsData: $heartSignsData")
            // call heart rate calculator
            val heartRate = HeartRateCalculator(heartSignsData).calculate()
            Log.d("heart", "heartRate: $heartRate")
            _heartResult.value = heartRate
            heartRate
        }
    }
}