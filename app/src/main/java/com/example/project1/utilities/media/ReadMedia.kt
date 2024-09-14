package com.example.project1.utilities.reader

import android.content.ContentUris
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.project1.utilities.dataclasses.ContextData
import com.example.project1.utilities.dataclasses.HeartSignsData
import com.example.project1.utilities.dataclasses.VideoData
import com.example.project1.utilities.dataclasses.VideoMetaData
import com.example.project1.utilities.interfaces.Reader

class ReadMedia(
    override val data: VideoMetaData,
) : Reader {
    // basically use the video file name to get uri and pass to HeartSignsData
    // gets video name from media data
    override fun read(contextData: ContextData): HeartSignsData {
        Log.d("video", "read: $data")
        val activity = contextData.activity
        val contentResolver = contextData.contentResolver
        val fileName = data.videoName
        // gets uri from media
        val projection: Array<String> = arrayOf(
            MediaStore.Video.Media._ID.toString(),
            MediaStore.Video.Media.DISPLAY_NAME.toString(),
            MediaStore.Video.Media.DURATION.toString(),
            MediaStore.Video.Media.SIZE.toString(),
        )
        Log.d("video", "projection: $projection")
        val selection: String = MediaStore.Video.Media.DISPLAY_NAME + " == ?"
        val selectionArgs: Array<String> = arrayOf("HeartRate.mp4")
        Log.d("video", "selectionArgs: $selectionArgs")
        Log.d("video", "selection: $selection")
        val videos = mutableListOf<VideoData>()
        if (activity != null) {
            ActivityCompat.requestPermissions(
                activity, arrayOf(
                    android.Manifest.permission.READ_MEDIA_VIDEO,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                ), 0
            )
        }
        Log.d("video", "contentResolver: $contentResolver")
        if (contentResolver != null) {
            contentResolver.query(
                Uri.parse(MediaStore.Video.Media.EXTERNAL_CONTENT_URI.toString()),
                projection,
                selection,
                selectionArgs,
                "${MediaStore.Video.Media.DISPLAY_NAME} ASC"
            ).use { cursor ->
                val nameColumn = cursor!!.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME)
                val durationColumn = cursor.getColumnIndex(MediaStore.Video.Media.DURATION)
                val sizeColumn = cursor.getColumnIndex(MediaStore.Video.Media.SIZE)
                val videoidColumn = cursor.getColumnIndex(MediaStore.Video.Media._ID)

                Log.d("video", "nameColumn: $nameColumn")
                while (cursor.moveToNext()) {
                    val id = cursor.getLong(videoidColumn)
                    val name = cursor.getString(nameColumn)
                    val duration = cursor.getInt(durationColumn)
                    val size = cursor.getInt(sizeColumn)
                    val uri = ContentUris.withAppendedId(
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id
                    )
                    Log.d("video", "uri: $uri")
                    Log.d("video", "name: $name")

                    videos.add(
                        VideoData(
                            videoName = name,
                            uri = uri,
                            videoDuration = duration,
                            videoSize = size,
                            videoId = id
                        )
                    )
                }

            }
        }
        val heartSignsData = contentResolver?.let {
            HeartSignsData(
                uri = videos[0].uri,
                contentResolver = it,
            )
        }
        Log.d("video", "read: $heartSignsData")
        return heartSignsData!!
    }
}