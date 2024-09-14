package com.example.project1.utilities.realm

import com.example.project1.utilities.dataclasses.SymptomsData
import com.example.project1.utilities.schema.SymptomSchema
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class SymptomRealm(private val context: CoroutineContext) {
    private val realm: Realm

    init {
        val config = RealmConfiguration.create(schema = setOf(SymptomSchema::class, SymptomsData::class))
        realm = Realm.open(config)
    }

    fun write(symptomData: SymptomSchema) {
        realm.writeBlocking {
            val managedSymptom = copyToRealm(symptomData)
        }
        CoroutineScope(context).async {
            realm.write {
                val managedSymptom = copyToRealm(symptomData)
            }
        }
    }
    fun close() {
        realm.close()
    }
}

// Other functions to read or manipulate data as needed

