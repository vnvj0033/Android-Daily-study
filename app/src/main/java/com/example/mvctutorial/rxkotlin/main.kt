package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject

fun main() {

    val subject: Subject<Int> = PublishSubject.create()

    subject.map { isEven(it) }
        .subscribe {
            println("The number is ${(if (it) "Even" else "Odd")}" )
        }

    subject.onNext(4)
    subject.onNext(9)
}

fun isEven(n: Int) : Boolean = (n % 2 == 0)
