package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

/**
 * Scheduler.io() : 파일 / network IO 작업을 할때 사용하는 용도입니다.
 * 내부적으로 cachedPool을 사용하기 때문에 thread가 동시에 계속 늘어나면서 생성될수 있으며, 유휴 thread가 있을 경우 재활용됩니다.
 *
 * Scheduler.computation() : cpu 의존적인 계산을 수행을 위한 thread pool을 사용합니다.
 * 코어개수만큼 thread pool을 만들어 사용합니다. (내부적으로 ForkJoinPool을 쓰는게 아닐지 예상해 봅니다..)
 *
 * Scheduler.newThread() : new Thread() 처럼 새로운 Thread를 하나 만들어 사용합니다.
 *
 * Scheduler.single() : singleThreadPool을 사용하므로, 해당 Scheduler로 여러 작업 수행시 Queuing 되어 순서가 보장됩니다.
 *
 * Scheduler.trampoline() : 호출을 수행한 thread를 이용하여 수행합니다.
 * 호출한 스레드 역시 단일 thread 이므로 여러 작업 요청시 Queuing 되어 순서가 보장됩니다.
 * 단, 호출한 스레드를 사용하기 때문에 queuing된 모든 작업이 끝나야만 다음 코드라인이 수행될 수 있습니다.
 *
 * Scheduler.from() :Executor를 전달하여 새로운 Scheduler를 생성할 수 있습니다.
 *
 * AndroidSchedulers.mainThread() : RxAndroid 사용시 mainThread()에서 수행하기 위한 Scheduler 입니다.
 * */

fun main() = runBlocking<Unit> {
    schedulers()
}

suspend fun schedulers() {

    val ob = Observable.just(1)

    ob.subscribeOn(Schedulers.io())
        .subscribe { println("Schedulers.io() - ${Thread.currentThread().name}") }

    ob.subscribeOn(Schedulers.computation())
        .subscribe { println("Schedulers.computation() - ${Thread.currentThread().name}") }

    ob.subscribeOn(Schedulers.newThread())
        .subscribe { println("Schedulers.newThread() - ${Thread.currentThread().name}") }

    ob.subscribeOn(Schedulers.single())
        .subscribe { println("Schedulers.single() - ${Thread.currentThread().name}") }

    ob.subscribeOn(Schedulers.trampoline())
        .subscribe { println("Schedulers.trampoline() - ${Thread.currentThread().name}") }

    val executor = Executors.newFixedThreadPool(2)
    val customScheduler = Schedulers.from(executor)

    ob.subscribeOn(customScheduler) .subscribe { println("Schedulers.from() - ${Thread.currentThread().name}") }

    delay(1000)

}
