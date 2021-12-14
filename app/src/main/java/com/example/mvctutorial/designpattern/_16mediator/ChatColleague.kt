package com.example.mvctutorial.designpattern._16mediator

import com.example.mvctutorial.designpattern._16mediator.content.Colleague

class ChatColleague: Colleague() {

    override fun handle(data: String) {
        println("$this-$data")
    }
}