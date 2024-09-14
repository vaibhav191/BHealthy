package com.example.project1.utilities.dataclasses

import com.example.project1.utilities.interfaces.iSigns

data class RespiratorySignsData(
    val accelValuesX: MutableList<Float>,
    val accelValuesY: MutableList<Float>,
    val accelValuesZ: MutableList<Float>
) : iSigns {

}
