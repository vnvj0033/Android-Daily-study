package com.example.mvctutorial.designpattern._08absttractfactory_guifactory.mac

import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.Button

class MacButton: Button {
    override fun onClick() {
        println("windows button clicked")
    }
}