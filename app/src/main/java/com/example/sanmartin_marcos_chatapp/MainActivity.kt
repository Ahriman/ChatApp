package com.example.sanmartin_marcos_chatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sanmartin_marcos_chatapp.ui.theme.Sanmartin_Marcos_ChatAppTheme

//const val startCountDefault = 0


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Sanmartin_Marcos_ChatAppTheme {


                // A surface container using the 'background' color from the theme
                Surface(
//                    modifier = Modifier.fillMaxSize(),
                    color = Color.Red //MaterialTheme.colors.background
                ) {

                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = "ChatApp")
                                }
                            )
                        },
                    ) { paddingValues ->
                        LazyColumn(
                            modifier = Modifier
//                                .fillMaxSize()
                                .background(Color.LightGray)
                                .padding(10.dp) //bottom = paddingValues.calculateBottomPadding()
//                            .verticalScroll(rememberScrollState())

                        ) {

                            paddingValues.calculateBottomPadding()
                            items(messages) { message ->
                                MyComponent(message)
                            }
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Sanmartin_Marcos_ChatAppTheme {
        Greeting("Android")
    }
}

@Composable
fun MyComponent(message: MyMessage) {

    /* Los mensajes del usuario local aparecen a la derecha y los del remoto a la izquierda. Además, 
    fijaos que dejo una esquina en los mensajes donde el usuario cambia, 
    pero que no está cuando el mismo usuario envía sucesivos mensajes. */

    val colorMessage = if (message.localMessage) Color(0xFF5ad6c4) else Color(0xFF88af5e)
    
    var showDate by remember { mutableStateOf(false) }
    var leftUpperCorner by remember { mutableStateOf(18.dp) }
    var rightUpperCorner by remember { mutableStateOf(18.dp) }

    if (message.localMessage) {
        leftUpperCorner = 18.dp
        rightUpperCorner = 0.dp

    } else {
        leftUpperCorner = 0.dp
        rightUpperCorner = 18.dp
    }

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.localMessage) Arrangement.End else Arrangement.Start
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
//            .background(Color.Yellow) //MaterialTheme.colors.secondary
                .padding(4.dp)
                .clickable(onClick = {
                    showDate = !showDate
                }),
            shape = RoundedCornerShape(leftUpperCorner, rightUpperCorner, 18.dp, 18.dp)
        ) {

            Column(
                Modifier
                    .background(colorMessage)
                    .padding(15.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = message.body,
                    color = Color.Black,
                )

                if (showDate) {
                    Spacer(modifier = Modifier.padding(top = 8.dp))
                    Text(
                        text = message.date,
                        color = Color.Black,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

        }

    }


}

data class MyMessage(
    var body: String,
    val localMessage: Boolean,
    val date: String,
    val showDate: Boolean? = false
)

val messages: List<MyMessage> = listOf(
    MyMessage(
        body = "You can think of Modifiers as implementations of the decorator pattern",
        localMessage = false,
        date = "01/01/2023 10:00"
    ),
    MyMessage(
        body = "You can think of Modifiers as implementations of the decorator pattern",
        localMessage = false,
        date = "01/01/2023 10:01"
    ),
    MyMessage(
        body = "You can think of Modifiers as implementations of the decorator pattern",
        localMessage = true,
        date = "01/01/2023 10:05"
    ),
    MyMessage(
        body = "You can think of Modifiers as implementations of the decorator pattern",
        localMessage = false,
        date = "01/01/2023 10:20"
    ),
    MyMessage(
        body = "You can think of Modifiers as implementations of the decorator pattern",
        localMessage = true,
        date = "01/01/2023 10:30"
    ),
    MyMessage(
        body = "You can think of Modifiers as implementations of the decorator pattern",
        localMessage = true,
        date = "01/01/2023 10:32"
    ),
    MyMessage(
        body = "You can think of Modifiers as implementations of the decorator pattern",
        localMessage = false,
        date = "01/01/2023 10:40"
    ),
    MyMessage(
        body = "You can think of Modifiers as implementations of the decorator pattern",
        localMessage = false,
        date = "01/01/2023 10:45"
    ),
    MyMessage(
        body = "You can think of Modifiers as implementations of the decorator pattern",
        localMessage = false,
        date = "01/01/2023 11:00"
    ),
    MyMessage(
        body = "You can think of Modifiers as implementations of the decorator pattern",
        localMessage = true,
        date = "01/01/2023 12:00"
    ),
    MyMessage(
        body = "You can think of Modifiers as implementations of the decorator pattern",
        localMessage = true,
        date = "01/01/2023 12:30"
    ),
    MyMessage(
        body = "You can think of Modifiers as implementations of the decorator pattern",
        localMessage = false,
        date = "01/01/2023 12:31"
    ),
)
