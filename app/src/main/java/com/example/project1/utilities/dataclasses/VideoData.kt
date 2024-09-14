package com.example.project1.utilities.dataclasses

import android.net.Uri

data class VideoData(
    val videoName: String,
    val uri: Uri,
    val videoDuration: Int,
    val videoSize: Int,
    val videoId: Long
)
