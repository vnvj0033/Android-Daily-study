package com.example.mvctutorial.designpattern._08absttractfactory_guifactory.windows;

import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.Button;

public class WindowsButton implements Button {
    @Override
    public void onClick() {
        System.out.println("windows button click");
    }
}
