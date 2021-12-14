package com.example.mvctutorial.designpattern._16mediator

import com.example.mvctutorial.designpattern._16mediator.content.Mediator

class ChatMediator: Mediator() {

    override fun mediate(data: String) {
        for (colleague in colleagues) {
            //중재가능성.
            colleague.handle(data)
        }
    }

}
