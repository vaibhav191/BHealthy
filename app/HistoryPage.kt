package com.example.project1.utilities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.project1.utilities.schema.SymptomSchema
import com.example.project1.utilities.ui.theme.Project1Theme
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import java.text.SimpleDateFormat
import java.util.Date

class HistoryPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        HistoryPage(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HistoryPage(modifier: Modifier = Modifier) {
    val config = RealmConfiguration.create(schema = setOf(SymptomSchema::class))
    val realm = Realm.open(config)
    val symptomsList = realm.query<SymptomSchema>().find()

    Column {
        // Table Header
        Row {
            Text("Date")
            Text("Heart Rate")
            Text("Respiratory Rate")
            Text("Nausea")
            Text("Headache")
            Text("Diarrhea")
            Text("Sore Throat")
            Text("Fever")
            Text("Muscle Ache")
            Text("Loss of Smell or Taste")
            Text("Cough")
            Text("Shortness of Breath")
            Text("Fatigue")
        }

        // Table Rows
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        symptomsList.forEach { symptom ->
            Row {
                val date = Date(symptom.timestamp)
                Text(dateFormat.format(date))
                Text(symptom.symptomsData?.heartRate.toString())
                Text(symptom.symptomsData?.respiratoryRate.toString())
                Text(symptom.symptomsData?.nausia.toString())
                Text(symptom.symptomsData?.headache.toString())
                Text(symptom.symptomsData?.diarrhea.toString())
                Text(symptom.symptomsData?.soreThroat.toString())
                Text(symptom.symptomsData?.fever.toString())
                Text(symptom.symptomsData?.muscleAche.toString())
                Text(symptom.symptomsData?.lossOfSmellOrTaste.toString())
                Text(symptom.symptomsData?.Cough.toString())
                Text(symptom.symptomsData?.ShortnessOfBreath.toString())
                Text(symptom.symptomsData?.Fatigue.toString())
            }
        }
    }

    realm.close()
}
