package com.example.mvctutorial.coroutines

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Channel은 두 Coroutine 사이에 정보를 교환하는 전달 객체이다.
 * */
fun main() {
//    channels()
    closingAndIterationOverChannels()
}

/**
 * Channel은 stream을 반환
 * Channel은 BlockingQueue와 유사하게 동작함
 * BlockingQueue의 put -> Channel의 send
 * BlockingQueue의 take -> Channel의 receive
 * */
private fun channels() = runBlocking {
    val channel = Channel<Int>()
    launch {
        // this might be heavy CPU-consuming computation or async logic, we'll just send five squares
        for (x in 1..5)
            channel.send(x * x)
    }
    // here we print five received integers:
    repeat(5) { println(channel.receive()) }
    println("Done!")
}


private fun closingAndIterationOverChannels() = runBlocking {
    val channel = Channel<Int>()
    launch {
        for (x in 1..5) channel.send(x * x)
        channel.close() // we're done sending
     }

    // here we print received values using `for` loop (until the channel is closed)
    for (y in channel) println(y)
    println("Done!")
}