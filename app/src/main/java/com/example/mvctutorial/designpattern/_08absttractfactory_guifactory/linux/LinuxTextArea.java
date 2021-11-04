package com.example.mvctutorial.designpattern._08absttractfactory_guifactory.linux;

import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.TextArea;

public class LinuxTextArea implements TextArea {
    @Override
    public String getText() {
        return "linux text area";
    }
}
