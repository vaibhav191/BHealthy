package com.example.project1.utilities.calculator

import com.example.project1.utilities.dataclasses.RespiratorySignsData
import com.example.project1.utilities.interfaces.iCalculator
import kotlin.math.abs
import kotlin.math.pow

class RespiratoryCalculator(
    override val signs: RespiratorySignsData
) : iCalculator {
    override fun calculate(): Int {
        val accelValuesX = signs.accelValuesX
        val accelValuesY = signs.accelValuesY
        val accelValuesZ = signs.accelValuesZ
        var previousValue: Float
        var currentValue: Float
        previousValue = 10f
        var k = 0
        for (i in 11..<accelValuesY.size) {
            currentValue = kotlin.math.sqrt(
                accelValuesZ[i].toDouble().pow(2.0) + accelValuesX[i].toDouble()
                    .pow(2.0) + accelValuesY[i].toDouble().pow(2.0)
            ).toFloat()
            if (abs(x = previousValue - currentValue) > 0.15) {
                k++
            }
            previousValue = currentValue
        }
        val ret = (k.toDouble() / 45.00)
        return (ret * 30).toInt()
    }
}