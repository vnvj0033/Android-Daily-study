package com.example.mvctutorial.designpattern._08absttractfactory_guifactory.linux

import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.Button

class LinuxButton: Button {
    override fun onClick() {
        println("windows button clicked")
    }
}