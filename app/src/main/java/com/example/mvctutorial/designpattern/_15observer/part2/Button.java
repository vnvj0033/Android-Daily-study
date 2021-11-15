package com.example.mvctutorial.designpattern._15observer.part2;

import java.util.Observable;

public class Button extends Observable {

    void onClick() {
        setChanged();
        notifyObservers();
    }
}
