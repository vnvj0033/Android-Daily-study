package com.example.mvctutorial.designpattern._08absttractfactory_guifactory.mac;

import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.TextArea;

public class MacTextArea implements TextArea {
    @Override
    public String getText() {
        return "mac text area";
    }
}
