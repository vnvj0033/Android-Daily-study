package com.example.mvctutorial.designpattern._08absttractfactory_guifactory.linux

import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.Button
import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.GuiFactory
import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.TextArea

class LinuxGuiFactory: GuiFactory {
    override fun createButton(): Button = LinuxButton()

    override fun createTextArea(): TextArea = LinuxTextArea()
}