package com.example.project1.utilities.runner

import android.app.Activity
import android.content.Context
import android.util.Log
import com.example.project1.utilities.calculator.RespiratoryCalculator
import com.example.project1.utilities.dataclasses.AxesEnum
import com.example.project1.utilities.dataclasses.ContextData
import com.example.project1.utilities.reader.Readcsv
import com.example.project1.utilities.dataclasses.csvData
import com.example.project1.utilities.interfaces.iRunner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class RespiratoryRunner(override val context: Context, override val activity: Activity): iRunner {
    private val _respiratoryResult = MutableStateFlow<Int?>(null)

    override val result: StateFlow<Int?> = _respiratoryResult.asStateFlow()
    override suspend fun run(): Int {
        // create csv data object to read csv files
        return withContext(Dispatchers.IO){
            val data = csvData(
                '\n'.toString(),
                files = hashMapOf(
                    AxesEnum.X to "CSVBreatheX.csv",
                    AxesEnum.Y to "CSVBreatheY.csv",
                    AxesEnum.Z to "CSVBreatheZ.csv"

                )
            )
            // read readcsv to obtain respiratory signs data
            val contextData = ContextData(
                context = context,
                activity = null,
                contentResolver = null,
            )
            var respiratorySignsData = Readcsv(data).read(contextData = contextData)

            // then call respiratory calculator
            var respiratoryRate = RespiratoryCalculator(respiratorySignsData).calculate()
            Log.d("respiratory", "respiratoryRate: $respiratoryRate")
            _respiratoryResult.value = respiratoryRate
            respiratoryRate
        }
    }

}