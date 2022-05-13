package com.example.mvctutorial.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select
import java.time.LocalDateTime

fun main() {
    selectingFromChannels()
}

/**
 * Select는 여러 개의 작업을 동시 시작하고 다음 요청이 없으면 대기하고 요청시 다시 처리하고 먼저 처리되는 값을 반환
 * 두 채널의 값을 기다리고 있다가 receive 가능 상태가 되면 해당 값을 읽어 처리합니다.
 * */
private fun selectingFromChannels() = runBlocking {
    val fizz = fizz()
    val buzz = buzz()
    repeat(7) {
        selectFizzBuzz(fizz, buzz)
    }
    coroutineContext.cancelChildren() // cancel fizz & buzz coroutines
}

suspend fun selectFizzBuzz(fizz: ReceiveChannel<String>, buzz: ReceiveChannel<String>) {
    select<Unit> { // means that this select expression does not produce any result
        fizz.onReceive { value -> // this is the first select clause
            println("fizz -> '$value'")
        }
        buzz.onReceive { value -> // this is the second select clause
            println("buzz -> '$value'")
        }
    }
}

fun CoroutineScope.fizz() = produce {
    while (true) { // sends "Fizz" every 300 ms
        delay(300)
        send("Fizz ${LocalDateTime.now()}")
    }
}

fun CoroutineScope.buzz() = produce {
    while (true) { // sends "Buzz!" every 500 ms
        delay(500)
        send("Buzz! ${LocalDateTime.now()}")
    }
}
