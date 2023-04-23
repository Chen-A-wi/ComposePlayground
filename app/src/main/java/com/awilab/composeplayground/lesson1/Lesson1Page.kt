package com.awilab.composeplayground.lesson1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.awilab.composeplayground.ui.theme.ComposePlaygroundTheme

@Composable
fun Greeting(name: String) {
    // 僅用 mutableStateOf 雖然會更改狀態，但 Recpmposition 隨時發生的，
    // 如果發生的當下未使用 remember 記住當下狀態只使用 mutableStateOf 的話
    // 物件會取用 default value 來使用，並不會取用 recomposition 前的狀態
    val expanded = remember { mutableStateOf(false) }
    val extraPadding = if (expanded.value) {
        48.dp
    } else {
        0.dp
    }

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f) // 指定佔用 Row 的比例
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello,")
                Text(text = name)
            }
            ElevatedButton(onClick = { expanded.value = expanded.value.not() }) {
                Text(
                    text = if (expanded.value) {
                        "See less"
                    } else {
                        "See more"
                    }
                )
            }
        }
    }
}

@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = listOf("World", "Compose")
) {
    Surface(
        modifier = modifier.padding(vertical = 4.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = modifier) {
            for (name in names) {
                Greeting(name = name)
            }
        }
    }
}

@Composable
fun Lesson1Page(
    modifier: Modifier = Modifier
) {
    var shouldShowOnBoarding by remember { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnBoarding) {
            OnBoardingScreen { // 因為只使用最後一個參數是可以使用lambda
                shouldShowOnBoarding = false
            }
        } else {
            Greetings()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePlaygroundTheme {
        Lesson1Page(modifier = Modifier.fillMaxSize())
    }
}