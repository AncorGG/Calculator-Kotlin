package com.example.calculadoraquesuma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculadoraquesuma.ui.theme.CalculadoraQueSumaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraQueSumaTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val firstNumber = remember { mutableStateOf("") }
    val secondNumber = remember { mutableStateOf("") }
    val result = remember { mutableStateOf("") }
    val errorMessage_1 = remember { mutableStateOf("") }
    val errorMessage_2 = remember { mutableStateOf("") }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            FirstNumberInput(firstNumber, errorMessage_1)
            SecondNumberInput(secondNumber, errorMessage_2)
            ButtonDisplay(firstNumber, secondNumber, result, errorMessage_1, errorMessage_2)
            Spacer(modifier = Modifier.height(16.dp))
            ResultDisplay(result)
        }
    }
}

@Composable
fun FirstNumberInput(firstNumber: androidx.compose.runtime.MutableState<String>,
                     errorMessage: androidx.compose.runtime.MutableState<String>) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Insert First Number: ",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = firstNumber.value,
            onValueChange = { firstNumber.value = it },
            label = { Text("Enter a number") },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(8.dp))

        if (errorMessage.value.isNotEmpty()) {
            ErrorTextDisplay(errorMessage)
        }
    }
}

@Composable
fun SecondNumberInput(secondNumber: androidx.compose.runtime.MutableState<String>,
                      errorMessage: androidx.compose.runtime.MutableState<String>) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Insert Second Number: ",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = secondNumber.value,
            onValueChange = { secondNumber.value = it },
            label = { Text("Enter a number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (errorMessage.value.isNotEmpty()) {
            ErrorTextDisplay(errorMessage)
        }
    }
}

@Composable
fun ButtonDisplay(
    firstNumber: androidx.compose.runtime.MutableState<String>,
    secondNumber: androidx.compose.runtime.MutableState<String>,
    result: androidx.compose.runtime.MutableState<String>,
    errorMessage_1: androidx.compose.runtime.MutableState<String>,
    errorMessage_2: androidx.compose.runtime.MutableState<String>

) {
    Button(
        onClick = {
            var hasError = false;

            val num1 = firstNumber.value.toFloatOrNull()
            val num2 = secondNumber.value.toFloatOrNull()

            if (num1 == null || firstNumber.value.length > 8) {
                errorMessage_1.value = "Invalid input. Please enter a number"
                hasError = true
            }else {
                errorMessage_1.value = "";
            }


            if (num2 == null || secondNumber.value.length > 8) {
                errorMessage_2.value = "Invalid input. Please enter a number"
                hasError = true
            }else {
                errorMessage_2.value = "";
            }

            if (hasError) return@Button

            result.value = "Result: ${num1!! + num2!!}";
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Calculate")
    }
}

@Composable
fun ResultDisplay(result: androidx.compose.runtime.MutableState<String>) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = result.value,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun ErrorTextDisplay(result: androidx.compose.runtime.MutableState<String>) {

    Text(
        text = result.value,
        style = MaterialTheme.typography.bodyLarge,
        color = Color.Red,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculadoraQueSumaTheme {
        MyApp()
    }
}
