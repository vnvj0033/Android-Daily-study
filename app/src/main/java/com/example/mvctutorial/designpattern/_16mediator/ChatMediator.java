package com.example.mvctutorial.designpattern._16mediator;

import com.example.mvctutorial.designpattern._16mediator.content.Colleague;
import com.example.mvctutorial.designpattern._16mediator.content.Mediator;

class ChatMediator extends Mediator {

    @Override
    public void mediate(String data) {
        for (Colleague colleague : colleagues) {
            //중재가능성.
            colleague.handle(data);
        }
    }

}