package com.example.mvctutorial.designpattern._16mediator.content;

import java.util.List;

public abstract class Mediator {
    private List<Colleague> colleagues;

    public boolean addColleague(Colleague colleague){
        if (colleague != null){

            return colleagues.add(colleague);
        }else{
            return false;
        }
    }

    public abstract void mediate(String data);
}