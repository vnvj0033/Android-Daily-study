package com.example.mvctutorial.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {
    flatMapLatest()
    delay(10000)
}

/** 코루틴에서 스레드 풀 만들기 */
private fun createCoroutineThreadPool() = runBlocking {
    val poolDispatcher = newFixedThreadPoolContext(3, "ThreadPool")
    val singleDispatcher = newSingleThreadContext("singleThread")
}

/** 안드로이드에서 Dispatcher */
private fun androidDispatcher() = runBlocking {
    CoroutineScope(Dispatchers.Main)
    CoroutineScope(Dispatchers.IO)
    CoroutineScope(Dispatchers.Default)
}

/** launch는 Job을 반환 */
private fun launch() = runBlocking {
    val job: Job = CoroutineScope(Dispatchers.Main).launch {  }
}

/** async는 Deferred(갑을 포함)를 반환 */
private fun async() = runBlocking {
    val deferredInt : Deferred<Int> = async { 1 }

    val value = deferredInt.await() // 1
}

/** 하나의 쓰레드에서 코루틴 스코프에 따른 순서 */
private fun progress() {
    val job1 = CoroutineScope(Dispatchers.IO).async {
        println("시작 job1")
        (1..10000).sortedByDescending { it }
    }

    val job2 = CoroutineScope(Dispatchers.Default).launch {
        println("시작 job2")
        job1.await()

        println("완료 job2")
    }

    val job3 = CoroutineScope(Dispatchers.Default).launch {
        println("시작 job3")
    }
}
/*
job1, job2, job3 동시 시작
job2 await 일시 중지 job1, job3 작업 진행
job3 job1 완료
job2에 await 값 받아 작업 진행
 */

/** 코루틴 일시정지는 코루틴 스코프 안에서 가능 suspend를 사용하면 코루틴 스코프에서만 사용할 수 있다 */
private suspend fun suspendFun() {
    val job = CoroutineScope(Dispatchers.IO).async {
        (1..10000).sortedByDescending { it }
    }

    CoroutineScope(Dispatchers.Default).launch {
        job.await()
        println("끝")
    }
}

/** 코루틴은 start = CoroutineStart.LAZY를 사용해 지연 가능  */
private fun lazy() {
    val job = CoroutineScope(Dispatchers.IO).launch(start = CoroutineStart.LAZY) {
        println("시작")
    }

    job.start()

    Thread.sleep(1000)
}

/** join을 사용하여 코루틴 스코프를 기다릴수 있다(현재 코루틴 일시정지) */
private suspend fun join() {
    val job = CoroutineScope(Dispatchers.IO).launch(start = CoroutineStart.LAZY) {
        println("시작")
    }

    job.join()
}

/** 코루틴은 Job.cancel을 통해서 취소 명령이 가능하다. */
private suspend fun cancel() {
    val job = CoroutineScope(Dispatchers.IO).launch {
        delay(1000)
    }

    job.cancel()

    delay(1000)
}


@OptIn(InternalCoroutinesApi::class)
private suspend fun cancelMessage() {
    val job = CoroutineScope(Dispatchers.IO).launch {
        delay(1000)
    }

    job.cancel("cancel by me")

    println(job.getCancellationException())
    delay(1000)
}

/** Job.invokeOnCompletion을 사용하면 thorw를 제어가능 */
private suspend fun cancelHandling() {
    val job = CoroutineScope(Dispatchers.IO).launch {
        delay(1000)
    }

    job.invokeOnCompletion { throwable ->
        when(throwable) {
            is CancellationException -> println("cancel")
            null -> println("completion")
        }
    }

    job.cancel("call cancel")

    delay(1000)
}

/** Job의 상태 확인 */
private fun jobStatus() {
    val job = CoroutineScope(Dispatchers.IO).launch {
        delay(1000)
    }

    job.isActive        // 실행중인지
    job.isCompleted     // 완료 or 취소 되었는지
    job.isCancelled     // 취소 요청 되었는지
}

/** CoroutineExceptionHandler는 CoroutineContext로 예외를 컨트롤 할 수 있다. */
private suspend fun coroutineExceptionHandle() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("context : $coroutineContext")
        println("throwable : $throwable")
    }

    val job = CoroutineScope(exceptionHandler).launch {
        throw IllegalArgumentException()
    }

    delay(1000)
}

/** Deferred는 Job 상속하고 마지막 값을 반환한다. */
private suspend fun deferred() {
    val deferred: Deferred<Int> = CoroutineScope(Dispatchers.IO).async {
        30
    }

    val value = deferred.await() // await는 coroutineScope를 일시정지하고 값을 받아온다.
    println(value)
}

/** deferred에서 예외를 컨트롤 하는 방법 */
private suspend fun deferredExceptionHandler() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        when(throwable) {
            is IllegalArgumentException -> println("call IllegalArgumentException")
            is InterruptedException -> println("call InterruptedException")
        }
    }

    val deferred: Deferred<List<Int>> = CoroutineScope(Dispatchers.IO).async {
        throw IllegalArgumentException()
        listOf(1, 2, 3)
    }

    CoroutineScope(exceptionHandler).launch {
        deferred.await()
    }
    delay(1000)
}

/** withContext 는 코루틴 스코프를 일시정지, 마지막 갑을 반환 (await 대신 사용 가능) */
private suspend fun withContext() {
    val result = withContext(Dispatchers.IO) {
        "async result"
    }

    println(result)
}

/** coroutineContext는 + operator로 확장할 수 있다. */
private fun coroutineContext() {
   val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->  }

    val coroutineContext = Dispatchers.IO + exceptionHandler

    CoroutineScope(coroutineContext).launch {  }
}

/** coroutine context 안에서 context key를 참조하여 접근 가능하다. */
private fun coroutineContextKey() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->  }

    val coroutineContext = Dispatchers.IO + exceptionHandler

    val exceptionHandlerFromContext = coroutineContext[exceptionHandler.key]

    if (exceptionHandler === exceptionHandlerFromContext) {
        println("is same!")
    }
}

/** coroutine context 에서 minus 를 사용해 key 를 사용해 제거를 사용 가능한다. */
private fun coroutineContextMinusKey() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->  }

    val coroutineContext = Dispatchers.IO + exceptionHandler

    val minusKey = coroutineContext.minusKey(exceptionHandler.key)

    if (minusKey === Dispatchers.IO) {
        println("is same!")
    }
}

/** supervisorJob을 이용하면 부모의 코루틴을 중단하지 않는다. */
private suspend fun supervisorJob() {
    val supervisor = SupervisorJob()

    CoroutineScope(Dispatchers.IO).launch {
        val firstChildJob = launch(Dispatchers.IO + supervisor) {
            throw AssertionError("assertion error")
        }

        val secondChildJob = launch(Dispatchers.Default) {
            delay(1000)
            println("second job is life")
        }

        firstChildJob.join()
        secondChildJob.join()
    }.join()
}
/*
firstChildJob은 중단 되지만
secondChildJob은 완료됨
 */


/** Flow의 생성자는 flow빌더로 만들 수 있다. */
private fun producer() {
    flow {
        (0..10).forEach {
            emit(it)
        }
    }
}


/** Flow의 중간 연상자 map, filter, onEach 등등 */
private fun flowIntermediary() {
    flow {
        (0..10).forEach {
            emit(it)
        }
    }.map { // 데이터 변경
        it + it
    }.filter { // 데이터 필터링
        true
    }.onEach { // 모든 데이터에 연산
        println("$it each")
    }
}

/** Flow는 collect를 사용해 소모처리를 한다. */
private suspend fun flowConsumer() {
    flow {
        (0..10).forEach {
            emit(it)
        }
    }.collect {
        println(it)
    }
}

/** Flow에 stateIn을 사용해 flow의 속성을 지정할 수 있음 */
private suspend fun state_in() {
    val stringFlow = flow {
        (0..1000).forEach {
            emit("Integer $it")
            delay(1000)
        }
    }

    val statusFlow = stringFlow.stateIn(
        initialValue = "integer 0",
        started = SharingStarted.WhileSubscribed(5000),
        scope = CoroutineScope(Dispatchers.IO)
    )

    statusFlow.collect {
        println(it)
    }
}

/** collectLatest를 사용하면 소비측 지연이 있을시 소비를 취소하고 다음 생성자를 전달한다. */
private suspend fun collect_latest() {
    val flow = flow {
        (0..10).forEach {
            emit("Integer $it")
            delay(300)
        }
    }

    flow.collectLatest {
        println(it)
        delay(1000)
    }
}

/** flow 생성자 소비자는 서로 작업을 기다리는 비효율성이 있어 buffer로 생성자 저장  */
private suspend fun buffer() {
    val flow = flow {
        (0..10).forEach {
            emit(it)
            delay(300)
        }
    }

    flow.onEach {
        println("emit $it")
    }.buffer().collect {
        delay(1000)
        println("collect $it")
    }

}

/** conflate는 flow의 가장 최근의 emit 값을 가져온다. */
private suspend fun conflate() {
    val flow = flow {
        (0..10).forEach {
            emit(it)
            delay(3000)
        }
    }

    flow.onEach {
        println("emit $it")
    }.conflate().collect {
        println("collect $it")
        delay(5000)
    }
}

/** flatMapConcat을 사용하면 새로운 flow 객체를 생성여변환한다. */
private suspend fun collectWithFlatMapConcat() {
    val flow = flow {
        emit(1)
        emit(5)
    }

    flow.flatMapConcat { initValue ->
        flow {
            emit(initValue + 1)
            emit(initValue + 2)
            emit(initValue + 3)
        }
    }.collect {
        println("collect $it")
    }
}


/** flatMapConcat의 생산이 오래 걸리면 소비가 지연됨 */
private suspend fun flatMapConcatLimit() {
    val flow = flow {
        emit(1)
        emit(5)
    }

    flow.flatMapConcat { initValue ->
        flow {
            emit(initValue + 1)
            delay(1000)
            emit(initValue + 2)
            emit(initValue + 3)
        }
    }.collect {
        println("collect $it")
    }
}

/**
 * flatMapLatest도 새로운 flow 객체를 생성여변환한다.
 * 다만 지연시 지연 취소되어 이전 데이터만 flow로 반환하여 넘긴다.
 * */
private suspend fun flatMapLatest() {
    val flow = flow {
        emit(1)
        emit(5)
    }

    flow.flatMapLatest { value->
        flow {
            emit(value + 1)
            delay(1000)
            emit(value + 2)
            emit(value + 3)
        }
    }.collect {
        println(it)
    }
}