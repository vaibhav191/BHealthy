package com.example.project1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.project1.utilities.dataclasses.SymptomsData
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
                Log.d("HistoryPage", "HistoryPage created")
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HistoryMainPage(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HistoryMainPage(modifier: Modifier = Modifier) {
    Log.d("HistoryPage", "HistoryPage Composable called")
    val config =
        RealmConfiguration.create(schema = setOf(SymptomSchema::class, SymptomsData::class))
    Log.d("HistoryPage", "Realm configuration created")
    val realm = Realm.open(config)
    Log.d("HistoryPage", "Realm opened")
    Log.d("HistoryPage", "$realm")
    val symptomsList = realm.query<SymptomSchema>().find()
    Log.d("HistoryPage", "Symptoms list retrieved")
    Log.d("HistoryPage", "$symptomsList")
    Log.d("HistoryPage", "Symptoms list size: ${symptomsList.size}")
    Row(modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize().verticalScroll(rememberScrollState())
        ) {
            // Table Header
            Row(
                Modifier
                    .fillMaxWidth()
            ) {
                TableCell(text = "Date", height = 50, width = 100, backgroundColor = Color.LightGray)
                TableCell(text = "Heart Rate", height = 50, width = 100, backgroundColor = Color.LightGray)
                TableCell(text = "Respiratory Rate", height = 50, width = 100, backgroundColor = Color.LightGray)
                TableCell(text = "Nausea", height = 50, width = 100, backgroundColor = Color.LightGray)
                TableCell(text = "Headache", height = 50, width = 100, backgroundColor = Color.LightGray)
                TableCell(text = "Diarrhea", height = 50, width = 100, backgroundColor = Color.LightGray)
                TableCell(text = "Sore Throat", height = 50, width = 100, backgroundColor = Color.LightGray)
                TableCell(text = "Fever", height = 50, width = 100, backgroundColor = Color.LightGray)
                TableCell(text = "Muscle Ache", height = 50, width = 100, backgroundColor = Color.LightGray)
                TableCell(text = "Loss of Smell or Taste", height = 50, width = 100, backgroundColor = Color.LightGray)
                TableCell(text = "Cough", height = 50, width = 100, backgroundColor = Color.LightGray)
                TableCell(text = "Shortness of Breath", height = 50, width = 100, backgroundColor = Color.LightGray)
                TableCell(text = "Fatigue", height = 50, width = 100, backgroundColor = Color.LightGray)
            }
            Log.d("HistoryPage", "Table header row added")
            // Table Rows
            val dateFormat = SimpleDateFormat("MM/dd/yyyy HH:mm")
            symptomsList.forEach { symptom ->
                Row(
                ) {
                    val date = Date(symptom.timestamp)
                    TableCell(text = dateFormat.format(date), height = 50, width = 100)
                    TableCell(
                        text = symptom.symptomsData?.heartRate.toString(),
                        height = 50,
                        width = 100
                    )
                    TableCell(
                        text = symptom.symptomsData?.respiratoryRate.toString(),
                        height = 50,
                        width = 100
                    )
                    TableCell(
                        text = symptom.symptomsData?.nausia.toString(),
                        height = 50,
                        width = 100
                    )
                    TableCell(
                        text = symptom.symptomsData?.headache.toString(),
                        height = 50,
                        width = 100
                    )
                    TableCell(
                        text = symptom.symptomsData?.diarrhea.toString(),
                        height = 50,
                        width = 100
                    )
                    TableCell(
                        text = symptom.symptomsData?.soreThroat.toString(),
                        height = 50,
                        width = 100
                    )
                    TableCell(
                        text = symptom.symptomsData?.fever.toString(),
                        height = 50,
                        width = 100
                    )
                    TableCell(
                        text = symptom.symptomsData?.muscleAche.toString(),
                        height = 50,
                        width = 100
                    )
                    TableCell(
                        text = symptom.symptomsData?.lossOfSmellOrTaste.toString(),
                        height = 50,
                        width = 100
                    )
                    TableCell(
                        text = symptom.symptomsData?.Cough.toString(),
                        height = 50,
                        width = 100
                    )
                    TableCell(
                        text = symptom.symptomsData?.ShortnessOfBreath.toString(),
                        height = 50,
                        width = 100
                    )
                    TableCell(
                        text = symptom.symptomsData?.Fatigue.toString(),
                        height = 50,
                        width = 100
                    )
                }
            }
            Log.d("HistoryPage", "Table rows added")
        }
        Log.d("HistoryPage", "Realm closing")
        realm.close()
    }
}
@Composable
fun RowScope.TableCell(
    text: String,
    modifier: Modifier = Modifier,
    height: Int,
    width: Int,
    color: Color = Color.White,
    backgroundColor: Color = Color.White
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .width(width.dp)
            .height(height.dp).background(backgroundColor),
        softWrap = true,
    )
}
