package com.example.mvctutorial.designpattern._08absttractfactory_guifactory.windows

import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.Button

class WindowsButton: Button {
    override fun onClick() {
        println("windows button clicked")
    }
}