package com.example.mvctutorial.designpattern._08absttractfactory_guifactory.mac

import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.TextArea

class MacTextArea: TextArea {
    override fun getText(): String = "windows text area"
}