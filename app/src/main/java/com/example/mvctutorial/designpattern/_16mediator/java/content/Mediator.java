package com.example.mvctutorial.designpattern._16mediator.java.content;

import java.util.ArrayList;
import java.util.List;

public abstract class Mediator {

    public List<Colleague> colleagues = new ArrayList<>();

    public boolean addColleague(Colleague colleague){
        if (colleague != null){
            return colleagues.add(colleague);
        }else{
            return false;
        }
    }

    public abstract void mediate(String data);
}