package com.example.mvctutorial.designpattern._08absttractfactory_guifactory.mac;

import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.Button;

public class MacButton implements Button {
    @Override
    public void onClick() {
        System.out.println("mac button click");
    }
}
