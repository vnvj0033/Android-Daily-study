package com.example.mvctutorial.designpattern._08absttractfactory_guifactory.mac;

import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.Button;
import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.GuiFactory;
import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.TextArea;

public class MacGuiFatory implements GuiFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public TextArea createTextArea() {
        return new MacTextArea();
    }
}
