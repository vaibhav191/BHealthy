package com.example.project1.utilities.reader

import com.example.project1.utilities.dataclasses.AxesEnum
import com.example.project1.utilities.dataclasses.ContextData
import com.example.project1.utilities.dataclasses.RespiratorySignsData
import com.example.project1.utilities.dataclasses.csvData
import com.example.project1.utilities.interfaces.Reader

class Readcsv(override val data: csvData) : Reader {
    override fun read(contextData: ContextData): RespiratorySignsData {
        val context = contextData.context
        val delimiter = this.data.delimiter
        val fileMap = this.data.files
        val xFileName = fileMap[AxesEnum.X]
        val yFileName = fileMap[AxesEnum.Y]
        val zFileName = fileMap[AxesEnum.Z]
        val dataX = xFileName?.let { context?.assets?.open(it)?.bufferedReader()?.readLines() }
            ?.map { it.toFloat() } as MutableList<Float>
        val dataY = yFileName?.let { context?.assets?.open(it)?.bufferedReader()?.readLines() }
            ?.map { it.toFloat() } as MutableList<Float>
        val dataZ = zFileName?.let { context?.assets?.open(it)?.bufferedReader()?.readLines() }
            ?.map { it.toFloat() } as MutableList<Float>

        val respiratorySignsData = RespiratorySignsData(dataX, dataY, dataZ)
        return respiratorySignsData
    }
}