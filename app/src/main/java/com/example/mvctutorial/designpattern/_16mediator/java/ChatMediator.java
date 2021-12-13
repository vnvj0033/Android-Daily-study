package com.example.mvctutorial.designpattern._16mediator.java;

import com.example.mvctutorial.designpattern._16mediator.java.content.Colleague;
import com.example.mvctutorial.designpattern._16mediator.java.content.Mediator;

class ChatMediator extends Mediator {

    @Override
    public void mediate(String data) {
        for (Colleague colleague : colleagues) {
            //중재가능성.
            colleague.handle(data);
        }
    }

}