package com.example.mvctutorial.designpattern._08absttractfactory_guifactory.linux

import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.TextArea

class LinuxTextArea: TextArea {
    override fun getText(): String = "windows text area"
}