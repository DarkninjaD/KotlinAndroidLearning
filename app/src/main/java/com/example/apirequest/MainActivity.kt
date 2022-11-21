package com.example.apirequest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.apirequest.ui.theme.APIRequestTheme
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      APIRequestTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
          App()
        }
      }
    }
  }
}

@Composable
fun App() {
  val execTest : ExecutorService = Executors.newFixedThreadPool(1)
  val isCard = rememberSaveable { mutableStateOf(false) }
  val cardData = rememberSaveable { mutableStateOf<Array<Card>>(arrayOf())}

  Column(modifier = Modifier
    .fillMaxWidth()
    .fillMaxHeight(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
  {
    if (isCard.value) {
      DisplayCard(cardData.value)
    }
    Button(modifier = Modifier
      .background(color = MaterialTheme.colors.background)
      , onClick = {
        val call = Callable{ drawCardsTest(1) }
        val raw = Executors.newSingleThreadExecutor().submit(call)
        cardData.value = raw.get()
        isCard.value = true
      }) {
      Text(text = "One Card")
    }
    Button(modifier = Modifier
      .background(color = MaterialTheme.colors.background)
      , onClick = {
        val call = Callable{ drawCardsTest(3) }
        val raw = Executors.newSingleThreadExecutor().submit(call)
        cardData.value = raw.get()
        isCard.value = true
      }) {
      Text(text = "Three Cards")
    }
    Button(modifier = Modifier
      .background(color = MaterialTheme.colors.background)
      , onClick = {
        val call = Callable{ drawCardsTest(5) }
        val raw = Executors.newSingleThreadExecutor().submit(call)
        cardData.value = raw.get()
        isCard.value = true}) {
      Text(text = "Five Cards")
    }
  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  APIRequestTheme {
    App()
  }
}
private fun drawCardsTest(numberOfCards: Number) : Array<Card> {

    val url = URL("https://rws-cards-api.herokuapp.com/api/v1/cards/random?n=$numberOfCards")
    val connection = url.openConnection() as HttpURLConnection
    if (connection.responseCode == 200) {
      val inputSystem = connection.inputStream
      val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")
      val request = Gson().fromJson(inputStreamReader, Request::class.java)
      inputStreamReader.close()t
      inputSystem.close()
      return request.cards
    }
  return arrayOf()
}



private fun drawCards(numberOfCards: Number) : Thread {
  return Thread {
    val url = URL("https://rws-cards-api.herokuapp.com/api/v1/cards/random?n=$numberOfCards")
    val connection = url.openConnection() as HttpURLConnection
    if (connection.responseCode == 200) {
      val inputSystem = connection.inputStream
      val inputStreamReader = InputStreamReader(inputSystem, "UTF-8")
      val request = Gson().fromJson(inputStreamReader, Request::class.java)
      inputStreamReader.close()
      inputSystem.close()
    }
  }
}
@Composable
fun DisplayCard(Cards : Array<Card>) {
  Cards.forEach {
      Text(text = it.name)
    }
}
