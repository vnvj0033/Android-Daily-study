package com.example.mvctutorial.designpattern._08absttractfactory_guifactory.windows;

import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.Button;
import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.GuiFactory;
import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.TextArea;
import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.mac.MacButton;
import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.mac.MacTextArea;

public class WindowsGuiFatory implements GuiFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public TextArea createTextArea() {
        return new WindowsTextArea();
    }
}
