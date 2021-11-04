package com.example.mvctutorial.designpattern._08absttractfactory_guifactory.concrete;

import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.GuiFactory;
import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.linux.LinuxGuiFactory;
import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.mac.MacGuiFatory;
import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.windows.WindowsGuiFatory;

public class FactoryInstance {
    public static GuiFactory getFactory(){

        String os = System.getProperty("os.name").toUpperCase();

        if (os.contains("MAC")) return new MacGuiFatory();
        if (os.contains("WIN")) return new WindowsGuiFatory();
        if (os.contains("LINUX")) return new LinuxGuiFactory();

        throw new RuntimeException("사용중인 OS를 찾을 수 없습니다. (넌 무슨 os를 사용하는것이냐?)");
    }
}
