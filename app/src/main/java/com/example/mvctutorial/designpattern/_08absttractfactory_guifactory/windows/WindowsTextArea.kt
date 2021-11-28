package com.example.mvctutorial.designpattern._08absttractfactory_guifactory.windows

import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.TextArea

class WindowsTextArea: TextArea {
    override fun getText(): String = "windows text area"
}