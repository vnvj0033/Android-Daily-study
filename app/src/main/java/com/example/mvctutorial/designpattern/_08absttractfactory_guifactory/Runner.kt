package com.example.mvctutorial.designpattern._08absttractfactory_guifactory

import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.concrete.FactoryInstance


fun main() {
//    val factory : GuiFactory = LinuxGuiFactory()
//    val factory : GuiFactory = MacFatory()
//    val factory : GuiFactory = WindowsGuiFatory()
    val factory = FactoryInstance.getFactory()


    val button = factory.createButton()
    val textArea = factory.createTextArea()

    button.onClick()
    println(textArea.getText())
}