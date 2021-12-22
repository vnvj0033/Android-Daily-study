package com.example.mvctutorial.rxkotlin

import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import java.util.*
import java.util.regex.Pattern

fun main() {
    println("Initial Out put with a = 15, b = 10")
    val calculator = ReactiveCalculator(15, 10)

    println("Enter a = <number> or b = <number> in separate lines\nexit to exit the program")
    var line: String?
    do {
        line = readLine()
        calculator.handleInput(line)
    } while (line != null && !line.lowercase(Locale.getDefault()).contains("exit"))
}

class ReactiveCalculator(a: Int, b: Int) {
    private val subjectAdd: Subject<Pair<Int, Int>> = PublishSubject.create()
    private val subjectSub: Subject<Pair<Int, Int>> = PublishSubject.create()
    private val subjectMult: Subject<Pair<Int, Int>> = PublishSubject.create()
    private val subjectDiv: Subject<Pair<Int, Int>> = PublishSubject.create()

    private val subjectCalc: Subject<ReactiveCalculator> = PublishSubject.create()

    private var nums: Pair<Int, Int> = Pair(0, 0)

    init {
        nums = Pair(a, b)

        subjectAdd.map { it.first + it.second }.subscribe { println("Add = $it") }
        subjectSub.map { it.first - it.second }.subscribe { println("Substract = $it") }
        subjectMult.map { it.first * it.second }.subscribe { println("Multiply = $it") }
        subjectDiv.map { it.first / (it.second * 1.0) }.subscribe { println("Divide = $it") }

        subjectCalc.subscribe {
            with(it) {
                subjectAdd.onNext(nums)
                subjectSub.onNext(nums)
                subjectMult.onNext(nums)
                subjectDiv.onNext(nums)
            }
        }

        subjectCalc.onNext(this)
    }

    fun handleInput(inputLine: String?) {

        if (!inputLine.equals("exit")) {
            val pattern: Pattern = Pattern.compile("([a|b])(?:\\s)?=(?:\\s)?(\\d*)")

            var a: Int? = null
            var b: Int? = null

            val matcher: java.util.regex.Matcher = pattern.matcher(inputLine)

            if (matcher.matches() && matcher.group(1) != null && matcher.group(2) != null) {
                if (matcher.group(1).lowercase(Locale.getDefault()) == "a") {
                    a = matcher.group(2).toInt()
                } else if (matcher.group(1).lowercase(Locale.getDefault()) == "b") {
                    b = matcher.group(2).toInt()
                }
            }


            when {
                a != null && b != null -> modifyNumbers(a, b)
                a != null -> modifyNumbers(a = a)
                b != null -> modifyNumbers(b = b)
                else -> println("Invalid Input")

            }
        }
    }

    private fun modifyNumbers(a: Int = nums.first, b: Int = nums.second) {
        nums = Pair(a, b)
        subjectCalc.onNext(this)

    }
}