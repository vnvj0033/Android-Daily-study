package com.example.mvctutorial.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// repeat : 코루틴 반복, 반복이 많아도 코루틴은 가벼움
fun main() = runBlocking {
    repeat(100000) { // launch a lot of coroutines
        launch {
            delay(1000L)
            print (".")
        }
    }
}
