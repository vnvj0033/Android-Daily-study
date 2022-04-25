package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce

/**
 * Channel은 두 Coroutine 사이에 정보를 교환하는 전달 객체이다.
 * */
fun main() {
//    channels()
//    closingAndIterationOverChannels()
//    buildingChannelProducers()
//    pipelines()
    primeNumbersWithPipeline()
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

/**
 * channel은 사용하지 않으면 close할 수 있다.
 * close를 하더라도 이전 send값은 보장됨
 * */
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

/**
 * coroutine으로 producer-consumer 패턴으로 표현가능
 * 생산하는 형태를 쉽게 구현하도록 제공하는 produce
 * 확장 함수 consumeEach로 소비하는쪽에서 사용
 * */
private fun buildingChannelProducers() = runBlocking {
    val squares = produce(Dispatchers.Default) {
        for (x in 1..5) send(x * x)
    }
    squares.consumeEach { println(it) }
    println("Done!")
}


/**
 * produce는 receive시에 쓰레드를 대기하여 값을 보장
 * */
private fun pipelines() = runBlocking {
    val numbers = produce {
        var x = 1
        while (true) send(x++)
    }
    val squares = produce {
        for (x in numbers) send(x * x)
    }
    for (i in 1..5) println(squares.receive()) // print first five
    println("Done!") // we are done
    coroutineContext.cancelChildren() // cancel children coroutines
}

/**
 * main thread에서 수행되기 때문에 cancelChildren을 통해서 10개만 골라내고 중지 합니다.
 * */
private fun primeNumbersWithPipeline() = runBlocking {
    var cur = numbersFrom(2)

    for (i in 1..10) {
        val prime = cur.receive()
        println(prime)
        cur = filter(cur, prime)
    }
    coroutineContext.cancelChildren() // cancel all children to let main finish }
}

fun CoroutineScope.numbersFrom(start: Int) = produce {
    var x = start
    while (true) send(x++) // infinite stream of integers from start
}

fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) =
    produce { for (x in numbers) if (x % prime != 0) send(x) }
