package com.example.project1

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project1.ui.theme.Project1Theme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project1Theme {

                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = { Nav() },
                    bottomBar = { Spacer(modifier = Modifier.padding(10.dp)) }
                ) { innerPadding ->
                    Home(modifier = Modifier.padding(innerPadding), context = LocalContext.current)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Nav(modifier: Modifier = Modifier) {
    val title_prefix = "B"
    val title = "Healthy"

    Row(
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
            .background(colorResource(R.color.white)),
    ) {
        Row(
            modifier = modifier.weight(0.5f)
        ) {

            Image(
                painter = painterResource(id = R.drawable.heartbeat), contentDescription = null,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(start = 5.dp, end = 5.dp)
            )
        }
        Row(
            modifier = modifier.weight(1f)
        ) {
            Text(
                text = "$title_prefix", modifier = modifier,
                fontWeight = FontWeight.Black,
                fontSize = 40.sp,
                textDecoration = TextDecoration.Underline
            )
            Text(
                text = "$title",
                modifier = modifier,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Cursive,
                fontSize = 40.sp,
            )

        }
    }

}

@Composable
fun Home(modifier: Modifier = Modifier, context: Context) {
    Column() {
        Image(
            painterResource(id = R.drawable.man),
            contentDescription = null,
            modifier = Modifier
                .weight(0.6f)
                .fillMaxWidth()
                .padding(10.dp)
        )
        Spacer(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .weight(0.1f)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                text = "John Doe",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                color = colorResource(R.color.teal_900)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                text = "25",
                fontWeight = FontWeight.Normal,
                color = colorResource(R.color.teal_900),
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                fontWeight = FontWeight.Normal,
                color = colorResource(R.color.teal_900),
                text = "Male", textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier
                .weight(0.4f)
                .fillMaxWidth(),
            Arrangement.Center,
            Alignment.CenterHorizontally

        )
        {
            Button(
                onClick = {
                    val recordVitality = Intent(context, RecordVitality::class.java)
                    context.startActivity(recordVitality)
                },
                modifier = Modifier,
                shape = CircleShape,
                colors = ButtonColors(
                    containerColor = colorResource(R.color.teal_700),
                    contentColor = colorResource(R.color.white),
                    disabledContainerColor = colorResource(R.color.gray),
                    disabledContentColor = colorResource(R.color.gray),
                )
            ) {
                Text(text = "Record", modifier = Modifier)
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(
                onClick = {
                    val historyIntent = Intent(context, HistoryPage::class.java)
                    context.startActivity(historyIntent)
                }, modifier = Modifier, shape = CircleShape,

                colors = ButtonColors(
                    containerColor = colorResource(R.color.teal_700),
                    contentColor = colorResource(R.color.white),
                    disabledContainerColor = colorResource(R.color.gray),
                    disabledContentColor = colorResource(R.color.gray),
                )
            ) {
                Text(text = "History", modifier = Modifier, textAlign = TextAlign.Justify)
            }

        }

    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview() {
    Column {
        Nav()
        Home(context = LocalContext.current)
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun Greeting(
    respiratoryRate: Int?,
    modifier: Modifier = Modifier,
    heartRate: Int
) {
    Box {

        Image(
            painter = painterResource(id = R.drawable.heartbeat),
            contentDescription = null,
            alpha = 0.9f,
            modifier = Modifier.fillMaxSize()
        )
        Column(
        ) {
            Image(painter = painterResource(id = R.drawable.man), contentDescription = null)
            Row {
                Text(
                    text = "Heart Rate: $heartRate", modifier = modifier
                )
                Text(
                    text = "Respiratory Rate: $respiratoryRate", modifier = modifier
                )

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    Project1Theme {
        Greeting(respiratoryRate = 10, modifier = Modifier.padding(16.dp), heartRate = 150)
    }
}



