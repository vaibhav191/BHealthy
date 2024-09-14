package com.example.project1.utilities.dataclasses

import io.realm.kotlin.types.RealmObject

class SymptomsData: RealmObject {
    var heartRate: Int = 0
    var respiratoryRate: Int = 0
    var nausia: Int = 0
    var headache: Int = 0
    var diarrhea: Int = 0
    var soreThroat: Int = 0
    var fever: Int = 0
    var muscleAche: Int = 0
    var lossOfSmellOrTaste: Int = 0
    var Cough: Int = 0
    var ShortnessOfBreath: Int = 0
    var Fatigue: Int = 0
}
