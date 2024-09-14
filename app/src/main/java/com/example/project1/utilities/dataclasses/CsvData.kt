package com.example.project1.utilities.dataclasses

import com.example.project1.utilities.interfaces.iFilesMetaData

data class csvData(val delimiter: String, val files: HashMap<AxesEnum, String>): iFilesMetaData {
}
