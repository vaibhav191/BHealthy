package com.example.project1

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.project1.ui.theme.Project1Theme
import com.example.project1.utilities.dataclasses.SymptomsData
import com.example.project1.utilities.realm.SymptomRealm
import com.example.project1.utilities.schema.SymptomSchema
import kotlinx.coroutines.Dispatchers
import kotlin.math.roundToInt

class SymptomsPage : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val heartRate = intent.getIntExtra("Heart Rate", 0)
            val respiratoryRate = intent.getIntExtra("Respiratory Rate", 0)
            Project1Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { Nav() },
                    bottomBar = { Spacer(modifier = Modifier.padding(10.dp)) }) { innerPadding ->
                    Main(modifier = Modifier.padding(innerPadding), context = this, heartRate = heartRate, respiratoryRate = respiratoryRate)
                }
            }
        }
    }
}

@Composable
fun Main(modifier: Modifier = Modifier, viewModel: SymptomsViewModel = SymptomsViewModel(), context: Context, heartRate: Int, respiratoryRate: Int) {
    Column {
        LazyColumn(
            modifier = modifier
                .padding(5.dp)
                .fillMaxHeight(0.9f), userScrollEnabled = true
        ) {
            val symptoms = arrayListOf(
                "Nausea",
                "Headache",
                "Diarrhea",
                "Sore Throat",
                "Fever",
                "Muscle Ache",
                "Loss of Smell or Taste",
                "Cough",
                "Shortness of Breath",
                "Fatigue"
            )
            items(symptoms.size) {
                symptomCard(symptoms[it], modifier = Modifier, viewModel)
            }
        }
        Button(
            onClick = {
                val values = viewModel.symptomValues
                Log.d("Symptoms", "values: $values")
                val symptomsData = SymptomsData().apply {
                    values.forEach{ (key, value) ->
                        when (key) {
                            "Nausea" -> nausia = value
                            "Headache" -> headache = value
                            "Diarrhea" -> diarrhea = value
                            "Sore Throat" -> soreThroat = value
                            "Fever" -> fever = value
                            "Muscle Ache" -> muscleAche = value
                            "Loss of Smell or Taste" -> lossOfSmellOrTaste = value
                            "Cough" -> Cough = value
                            "Shortness of Breath" -> ShortnessOfBreath = value
                            "Fatigue" -> Fatigue = value
                            else -> {
                                Log.d("Symptoms", "Unknown symptom: $key")
                            }
                        }
                        Log.d("Symptoms", "$key: $value")
                    }

                }
                Log.d("Symptoms", "Heart Rate: $heartRate, Respiratory Rate: $respiratoryRate")
                symptomsData.heartRate = heartRate
                symptomsData.respiratoryRate = respiratoryRate
                var symptomsSchemaData = SymptomSchema()
                symptomsSchemaData.symptomsData = symptomsData
                val symptomRealm = SymptomRealm(Dispatchers.Main)
                symptomRealm.write(symptomsSchemaData)
                symptomRealm.close()
                Log.d("Symptoms", "Data written to Realm")
                val mainActivityIntent = Intent(context, MainActivity::class.java)
                context.startActivity(mainActivityIntent)
            }, modifier = Modifier
                .fillMaxHeight(0.4f)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("SUBMIT", fontSize = 15.sp)
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun symptomCard(name: String, modifier: Modifier = Modifier, viewModel: SymptomsViewModel) {
    Card(modifier = modifier.padding(5.dp), elevation = CardDefaults.cardElevation(10.dp))
    {
        Column(Modifier)
        {
            Text(text = name)
            var sliderPosition by remember { mutableFloatStateOf(0f) }
            Column {
                Slider(
                    value = sliderPosition,
                    onValueChange = {
                        sliderPosition = it
                        viewModel.updateSymptomValue(name, sliderPosition.roundToInt())
                    },
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.secondary,
                        activeTrackColor = MaterialTheme.colorScheme.secondary,
                        inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                    ),
                    steps = 4,
                    valueRange = 0f..5f
                )
                Text(text = sliderPosition.roundToInt().toString())
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainPreview() {
    Main(Modifier, SymptomsViewModel(), LocalContext.current, 150, 10)
}

class SymptomsViewModel : ViewModel() {
    private val _symptomValues = mutableStateMapOf<String, Int>()
    val symptomValues: Map<String, Int> = _symptomValues

    fun updateSymptomValue(symptomName: String, value: Int) {
        _symptomValues[symptomName] = value
    }
}