package com.example.mvctutorial.designpattern.factory

fun main() {

    val creator = DefaultItemCreator()

    val item1 = creator.create("나무칼")
    val item2 = creator.create("나무방패")
    val item3 = creator.create("나무갑주")

    item1.use()
    item2.use()
    item3.use()
}
