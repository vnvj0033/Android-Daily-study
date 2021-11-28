package com.example.mvctutorial.designpattern._08absttractfactory_guifactory

interface GuiFactory {

    fun createButton(): Button
    fun createTextArea(): TextArea
}