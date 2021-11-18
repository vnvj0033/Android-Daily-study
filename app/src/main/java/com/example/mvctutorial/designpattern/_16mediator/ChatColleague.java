package com.example.mvctutorial.designpattern._16mediator;

import com.example.mvctutorial.designpattern._16mediator.content.Colleague;

public class ChatColleague extends Colleague {

    @Override
    public void handle(String data) {
        System.out.println(this+"-"+data);
    }

}
