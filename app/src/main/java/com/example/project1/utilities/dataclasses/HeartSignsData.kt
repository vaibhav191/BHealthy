package com.example.project1.utilities.dataclasses

import android.content.ContentResolver
import android.net.Uri
import com.example.project1.utilities.interfaces.iSigns

data class HeartSignsData(val uri: Uri,
                          val contentResolver: ContentResolver
) : iSigns {

}
