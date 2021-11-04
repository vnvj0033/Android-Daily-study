package com.example.mvctutorial.designpattern._08absttractfactory_guifactory.linux;

import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.Button;
import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.GuiFactory;
import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.TextArea;

public class LinuxGuiFactory implements GuiFactory {
    @Override
    public Button createButton() {
        return new LinuxButton();
    }

    @Override
    public TextArea createTextArea() {
        return new LinuxTextArea();
    }
}
