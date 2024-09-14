package com.example.project1.utilities.interfaces

import com.example.project1.utilities.dataclasses.ContextData

interface Reader {
    val data: iFilesMetaData
    fun read(context: ContextData): iSigns
}