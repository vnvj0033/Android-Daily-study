package com.example.mvctutorial.designpattern._08absttractfactory_guifactory.linux;

import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.Button;

public class LinuxButton implements Button {
    @Override
    public void onClick() {
        System.out.println("linux button click");
    }
}
