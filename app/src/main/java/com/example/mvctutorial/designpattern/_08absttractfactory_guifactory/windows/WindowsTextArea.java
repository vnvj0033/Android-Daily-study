package com.example.mvctutorial.designpattern._08absttractfactory_guifactory.windows;

import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.TextArea;

public class WindowsTextArea implements TextArea {
    @Override
    public String getText() {
        return "windows text area";
    }
}
