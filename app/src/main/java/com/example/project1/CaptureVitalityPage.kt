package com.example.project1

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.project1.utilities.interfaces.iRunner
import com.example.project1.utilities.runner.HeartRunner
import com.example.project1.utilities.runner.RespiratoryRunner
import com.example.project1.utilities.ui.theme.Project1Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RecordVitality : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project1Theme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = { Nav() },
                    bottomBar = { Spacer(modifier = Modifier.padding(10.dp)) }) { innerPadding ->
                    MainPage(
                        context = this, modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainPage(modifier: Modifier, context: Context) {
    Column(
        modifier.fillMaxHeight(),
        Arrangement.Center,
        Alignment.CenterHorizontally,
    ) {
        val symptomsIntent = Intent(context, SymptomsPage::class.java)
        val heartRunner = HeartRunner(activity = context as ComponentActivity, context = context)
        val respiratoryRunner =
            RespiratoryRunner(activity = context as ComponentActivity, context = context)
//        val heartResult by heartRunner.result.collectAsState()
//        val respiratoryResult by respiratoryRunner.result.collectAsState()
        Cardf(
            "Heart Rate",
            modifier
                .weight(0.3f)
                .fillMaxWidth(),
            context,
            heartRunner,
            symptomsIntent
        )
        Spacer(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
        Cardf(
            "Respiratory Rate",
            modifier
                .weight(0.3f)
                .fillMaxWidth(),
            context,
            respiratoryRunner
        )
        Spacer(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier.weight(0.2f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                // symptomsIntent.putExtra("heartRate", heartResult)
                // symptomsIntent.putExtra("respiratoryRate", respiratoryResult)
                symptomsIntent.putExtra("Heart Rate", heartRunner.result.value)
                symptomsIntent.putExtra("Respiratory Rate", respiratoryRunner.result.value)
                context.startActivity(symptomsIntent)
            }, modifier = Modifier) {
                Text("Symptoms")
            }
        }
    }
}

@Composable
fun Cardf(
    name: String,
    modifier: Modifier,
    context: Context,
    runner: iRunner,
    intent: Intent? = null
) {
    val result by runner.result.collectAsState()
    var isLoading by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(10.dp)
    )
    {
        Row(
            modifier = modifier.padding(10.dp), Arrangement.SpaceEvenly, Alignment.CenterVertically
        ) {
            Text(
                text = when {
                    isLoading -> "Calculating $name..."
                    result != null -> "$name: $result"
                    else -> "Calculate $name"
                }
            )
            if (result == null && !isLoading) {
                Button(
                    onClick = {
                        isLoading = true
                        GlobalScope.launch(Dispatchers.Main) {  // Launch a coroutine
                            runner.run()
                            isLoading = false
                        }
                    }, modifier = Modifier
                ) {
                    Text("BEGIN")
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainPagePreview() {
    Column {
        Nav(Modifier)
        MainPage(Modifier, RecordVitality())
    }
}