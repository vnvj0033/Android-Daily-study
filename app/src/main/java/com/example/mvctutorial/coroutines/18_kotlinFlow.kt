package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

fun main() {
//    representingMultipleValues()
//    sequences()
//    asynchronousFlow()
//    flowsAreCold()
//    flowCancellation()
//    flowBuilders()
//    intermediateFlowOperators()
//    transformOperator()
//    sizeLimitingOperators()
//    terminalFlowOperators()
//    flowsAreSequential()
//    flowContext()
//    flowOnOperator()
//    buffering()
//    conflation()
//    processingTheLatestValue()
//    zip()
//    combine()
//    combine2()
//    flatMapConcat()
//    flatMapMerge()
    flatMapLatest()
}

/**
 * 다수의 값은 코틀린 콜렌션을 통해 가능하다. 다음은 List를 forEach를 사용해 출력
 * */
private fun representingMultipleValues() = runBlocking {
    listOf(1, 2, 3).forEach { value -> println(value) }
}
// 1
// 2
// 3


/**
 * 자원이 소모되는 작업에는 시퀀스를 이용할 수 있다.
 * Sequence는 객체를 생성하는 시간을 yield를 만나 값을 전달하고 객체가 생성까지 지연시킨다.
 * */
private fun sequences() = runBlocking {

    val foo: Sequence<Int> = sequence { // sequence builder
        for (i in 1..3) {
            Thread.sleep(1000) // pretend we are computing it
            yield(i) // yield next value
        }
    }

    foo.forEach { println(it) }
}
// 1
// 2
// 3

/**
 * Flow는 flow {}, 빌더를 사용해 생성하며 빌더중 코드는 언제든 중단이 가능함
 * 값은 emit를 사용해 배출하며 collect를 통해 방출
 * */
private fun asynchronousFlow() = runBlocking {

    val foo: Flow<Int> = flow { // flow builder
        delay(100)
        for (i in 1..3) {
            delay(1000) // pretend we are doing something useful here
            emit(i) // emit next value
        }
    }

    println("main start!")
    launch {
        for (k in 1..3) {
            delay(1000)
            println("I'm not blocked $k")
        }
    }

    foo.collect { println(it) }
    println("main end!")
}
/*
main start!
I'm not blocked 1
1
I'm not blocked 2
2
I'm not blocked 3
3
main end!
 */


/**
 * Flow는 sequence 처럼 cold stream이다
 * collect가 실행 되기 전까지 대기한다.
 * collect가 다시 호출되면 초기화 실행
 * */
private fun flowsAreCold() = runBlocking {

    val foo: Flow<Int> = flow {
        for (i in 1..3) {
            delay(100)
            emit(i)
        }
    }

    println("Calling collect...")
    foo.collect { println(it) }

    println("Calling collect again...")
    foo.collect { println(it) }
}
/*
Calling collect...
1
2
3
Calling collect again...
1
2
3
*/


/**
 * flow 자체에는 cancel 함수를 지원하지 않아
 * 아래와 같이 타이머로 종료시키거나 launch로 감싸서 취소
 * */
private fun flowCancellation() = runBlocking {
    val flow = flow {
        for (i in 1..3) {
            delay(100)
            emit(i)
        }
    }

    withTimeoutOrNull(250) { // Timeout after 250ms
        flow.collect { println("Emitting $it") }
    }
    println("flow1 Done")


    val job = launch { // Timeout after 250ms
        flow.collect { println("Emitting $it") }
    }
    delay(250)
    job.cancel()
    println("flow2 Done")
}
/*
Emitting 1
Emitting 2
flow1 Done
Emitting 1
Emitting 2
flow2 Done
 */


/**
 * flow{...}를 이용해서 flow를 만드는건 가장 기본적인 방법
 * 값이 고정되어 있을경우 flowOf
 * 다양한 Collection들을 .asFlow() extension function으로 flow로 변경
 * */
private fun flowBuilders() = runBlocking {
    println("main start!")

    val flow = flowOf(1, 2, 3)
    flow.collect { println("flow : $it") }

    println("") // Convert an integer range to a flow
    (1..3).asFlow().collect { println("flow : $it") }
    println("main end!")
}
/*
main start!
flow : 1
flow : 2
flow : 3

flow : 1
flow : 2
flow : 3
main end!
 */


/**
 * flow에서 사용되는 map이나 filter의 블럭 안에서 delay 같은 suspending function을 사용 가능
 * */
private fun intermediateFlowOperators() = runBlocking {
    (1..3).asFlow()
        .map { request -> performRequest(request) }
        .collect { response -> println(response) }
}

suspend fun performRequest(request: Int): String {
    delay(1000) // imitate long-running asynchronous work
    return "response $request"
}
/*
response 1
response 2
response 3
 */


/**
 * transform은 map 이나 filter처럼 간단하게 값들을 변환할 수 도 있고, 복잡한 변환을 수행하도록 할수도 있음
 * */
private fun transformOperator() = runBlocking {
    (1..3).asFlow()
        .transform { request ->
            emit("Making request $request")
            emit(performRequest(request))
        }
        .collect { response -> println(response) }
}
/*
Making request 1
response 1
Making request 2
response 2
Making request 3
response 3
 */


/**
 * 몇개의 값만 처리가 필요한 경우 take를 통하여 개수를 제한
 * take는 제한된 개수까지만 flow를 수행하고 그 이후에는 cancel
 * finally를 사용해 리소스를 관리할 수 있음
 * */
private fun sizeLimitingOperators() = runBlocking {
    val numbers = flow {
        try {
            emit(1)
            emit(2)
            println("This line will not execute")
            emit(3)
        } finally {
            println("Finally in numbers")
        }
    }

    numbers.take(2).collect { println(it) }
}
/*
1
2
Finally in numbers
 */


/**
 * flow는 다양한 collection 을 지원
 * toList 또는 toSet : flow를 MutableList나 MutableSet으로 변환
 * first: 첫번째 원소를 반환하고 나머지는 cancel 시킴
 * reduce: 첫번째 원소에 주어진 operation을 이용하여 누적시켜 최종값을 반환
 * fold: 초기값을 입력받아 주어진 operation을 이용하여 누적시켜 최종값을 반환
 * */
private fun terminalFlowOperators() = runBlocking {
    val sum = (1..5).asFlow()
        .map { it * it } // squares of numbers from 1 to 5
        .reduce { a, b -> a + b } // sum them (terminal operator)
    println(sum)
}
/*
55
 */


/**
 * 각각의 colection으로 이루어진 flow들은 순차적으로(sequential)하게 동작
 * */
private fun flowsAreSequential() = runBlocking {
    (1..5).asFlow()
        .filter {
            println("Filter $it")
            it % 2 == 0
        }.map {
            println("Map $it")
            "string $it"
        }.collect {
            println("Collect $it")
        }
}
/*
Filter 1
Filter 2
Map 2
Collect string 2
Filter 3
Filter 4
Map 4
Collect string 4
Filter 5
 */


/**
 * flow로 만들어진 collection은 이를 호출한 caller의 coroutine context에서 수행되며
 * 이를 context preservation(context 보존)이라함
 * */
private fun flowContext() = runBlocking {
    val foo = flow {
        emit(Thread.currentThread().name)
    }

    foo.collect {
        println("start thread : [$it]")
        println("Collect thread : [${Thread.currentThread().name}]")
    }

    launch(Dispatchers.IO) {
        foo.collect {
            println("start thread : [$it]")
            println("Collect thread : [${Thread.currentThread().name}]")
        }
    }
}
/*
start thread : [main]
Collect thread : [main]
start thread : [DefaultDispatcher-worker-1]
Collect thread : [DefaultDispatcher-worker-1]
 */


/**
 * background thread에서 수행하고, 결과를 받는 작업은 main thread에서 처리할 경우
 * withContext가 아니라 flowOn 을 이용하여 context를 바꿔줄 수 있음
 * */
private fun flowOnOperator() = runBlocking {

    val foo = flow {
        emit(Thread.currentThread().name)
    }.flowOn(Dispatchers.Default) // RIGHT way to change context for CPU-consuming code in flow builder

    foo.collect {
        println("start thread : [$it]")
        println("Collect thread : [${Thread.currentThread().name}]")
    }
}
/*
start thread : [DefaultDispatcher-worker-1]
Collect thread : [main]
 */


/**
 * emit() 하는 부분에 buffer를 만들고 순차적인 처리가 아닌 pipelining을 통해 동시에 동작하도록 하여 시간을 감소
 * */
private fun buffering() = runBlocking {
    val foo: Flow<Int> = flow {
        for (i in 1..3) {
            delay(1000) // pretend we are asynchronously waiting 1000 ms
            emit(i) // emit next value
        }
    }

    val time = measureTimeMillis {
        foo.buffer().collect { value ->
            delay(100) // pretend we are processing it for 100 ms
            println(value)
        }
    }
    println("Collected in $time ms")
}
/*
1
2
3
Collected in 3150 ms
 */


/**
 * conflate operator를 사용하여 중간값은 skip하도록 구현
 * 값을 처리하는 시점에 emit되어 쌓여있는 중간 값은 모두 버리고 마지막 값만 취함
 * */
private fun conflation() = runBlocking {

    val foo: Flow<Int> = flow {
        for (i in 1..3) {
            delay(100) // pretend we are asynchronously waiting 100 ms
            emit(i) // emit next value
            println("emit $i")
        }
    }

    val time = measureTimeMillis {
        foo.conflate()
            .collect { value ->
                try {
                    delay(300) // pretend we are processing it for 300 ms
                    println("Done $value")
                } catch (ce: CancellationException) {
                    println("Cancelled $value")
                }
            }
    }
    println("Collected in $time ms")
}
/*
emit 1
emit 2
emit 3
Done 1
Done 3
Collected in 753 ms
 */


/**
 * collectLatest:
 * collector가 flow를 처리하기 전에 다른 값을 전달 받으면 이전 collector를 취소하고 새로 전달받은 값을 처리하도록 재시작
 * CancellationException으로 취소 catch해 처리 가능
 * */
private fun processingTheLatestValue() = runBlocking {

    val foo: Flow<Int> = flow {
        for (i in 1..3) {
            delay(100) // pretend we are asynchronously waiting 100 ms
            emit(i) // emit next value
        }
    }

    val time = measureTimeMillis {
        foo.collectLatest { value ->
            try {
                println("collect $value")
                delay(300) // pretend we are processing it for 300 ms
                println("Done $value")
            } catch (ce: CancellationException) {
                println("Cancelled $value")
            }
        }
    }
    println("Collected in $time ms")
}
/*
collect 1
Cancelled 1
collect 2
Cancelled 2
collect 3
Done 3
Collected in 649 ms
 */



/**
 * zip은 두개의 flow를 병합하는 작업을 제공
 * 앞쪽 원소부터 하나씩 처리하고 뒤에 원소를 처리해 collect로 전달
 * */
private fun zip() = runBlocking {
    val nums = (1..3).asFlow() // numbers 1..3
    val strs = flowOf("one", "two", "three") // strings
    nums.zip(strs) { a, b -> "$a -> $b" } // compose a single string
        .collect { println(it) } // collect and print
}
/*
1 -> one
2 -> two
3 -> three
 */



/**
 * combine은 두개의 flow를 병합하는 작업을 제공
 * emit이 일어날 때마다 collect를 호출, 단 병합 flow 모두 하나의 방출값은 있어야 한다.
 * */
private fun combine() = runBlocking {
    val nums = (1..3).asFlow().onEach { delay(300) } // numbers 1..3 every 300 ms
    val strs = flowOf("one", "two", "three").onEach { delay(400) } // strings every 400 ms
    val startTime = System.currentTimeMillis() // remember the start time
    nums.combine(strs) { a, b -> "$a -> $b" } // compose a single string with "combine"
        .collect { value -> // collect and print
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}
/*
1 -> one at 432 ms from start
2 -> one at 638 ms from start
2 -> two at 837 ms from start
3 -> two at 939 ms from start
3 -> three at 1240 ms from start
 */

private fun combine2() = runBlocking {
    val nums = (1..3).asFlow().onEach { delay(100) } // numbers 1..3 every 300 ms
    val strs = flowOf("one", "two", "three", "four").onEach { delay(400) } // strings every 400 ms
    val startTime = System.currentTimeMillis() // remember the start time
    nums.combine(strs) { a, b -> "$a -> $b" } // compose a single string with "zip"
        .collect { value -> // collect and print
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}
/*
3 -> one at 456 ms from start
3 -> two at 863 ms from start
3 -> three at 1265 ms from start
3 -> four at 1669 ms from start
 */


/**
 * flatMapConcat은 flow를 연결하는 연산자이다.
 * 외부 flow의 순서를 보장한다.
 * */
private fun flatMapConcat() = runBlocking {
    fun requestFlow(i: Int): Flow<String> = flow {
        emit("$i: First")
        delay(500) // wait 500 ms
        emit("$i: Second")
    }

    val startTime = System.currentTimeMillis() // remember the start time
    (1..3).asFlow().onEach { delay(100) } // a number every 100 ms
        .flatMapConcat { requestFlow(it) }
        .collect { value -> // collect and print
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}
/*
1: First at 129 ms from start
1: Second at 631 ms from start
2: First at 732 ms from start
2: Second at 1233 ms from start
3: First at 1335 ms from start
3: Second at 1840 ms from start
 */

/**
 * flatMapMerge 역시 flow를 연결한다.
 * 단 외부 flow의 순서를 보장하지 않고 먼저 emit된 값을 전달한다.
 * */
private fun flatMapMerge() = runBlocking {

    fun requestFlow(i: Int): Flow<String> = flow {
        emit("$i: First")
        delay(500) // wait 500 ms
        emit("$i: Second")
    }

    val startTime = System.currentTimeMillis() // remember the start time
    (1..3).asFlow().onEach { delay(100) } // a number every 100 ms
        .flatMapMerge { requestFlow(it) }
        .collect { value -> // collect and print
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}
/*
1: First at 188 ms from start
2: First at 272 ms from start
3: First at 377 ms from start
1: Second at 688 ms from start
2: Second at 773 ms from start
3: Second at 880 ms from start
 */


/**
 * flow에서 emit 발생시 이전에 대기중이거나 동작중인 flow는 cancel
 * requestFlow의 작업이 길어 second가 cancel됨을 볼 수 있다.
 * */
private fun flatMapLatest() = runBlocking {
    fun requestFlow(i: Int) = flow {
        try {
            emit("$i: First")
            delay(500) // wait 500 ms
            emit("$i: Second")
        } catch (ce: CancellationException) {
            println("cancelled!!")
        }
    }

    val startTime = System.currentTimeMillis() // remember the start time
    (1..3).asFlow().onEach { delay(100) } // a number every 100 ms
        .flatMapLatest { requestFlow(it) }
        .collect { value -> // collect and print
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}
/*
1: First at 154 ms from start
cancelled!!
2: First at 261 ms from start
cancelled!!
3: First at 363 ms from start
3: Second at 867 ms from start
 */