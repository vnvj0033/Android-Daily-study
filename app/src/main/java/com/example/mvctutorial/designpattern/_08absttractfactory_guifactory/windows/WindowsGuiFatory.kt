package com.example.mvctutorial.designpattern._08absttractfactory_guifactory.windows

import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.Button
import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.GuiFactory
import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.TextArea
import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.linux.LinuxButton
import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.linux.LinuxTextArea

class WindowsGuiFatory: GuiFactory {
    override fun createButton(): Button = LinuxButton()

    override fun createTextArea(): TextArea = LinuxTextArea()
}