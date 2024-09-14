package com.example.project1.utilities.schema

import com.example.project1.utilities.dataclasses.SymptomsData
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class SymptomSchema: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()

    var timestamp: Long = System.currentTimeMillis()
    var symptomsData: SymptomsData? = null
}