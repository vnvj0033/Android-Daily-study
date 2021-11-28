package com.example.mvctutorial.designpattern._08absttractfactory_guifactory.concrete

import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.GuiFactory
import com.example.mvctutorial.designpattern._08absttractfactory_guifactory.linux.LinuxGuiFactory
import java.lang.RuntimeException

class FactoryInstance {

    companion object {
        fun getFactory(): GuiFactory {
            val os = System.getProperty("os.name")?.uppercase()

            os?.run {
                if (contains("MAC")) return LinuxGuiFactory()
                if (contains("WIN")) return LinuxGuiFactory()
                if (contains("LINUX")) return LinuxGuiFactory()

            }
            throw RuntimeException("사용중인 OS를 찾을 수 없습니다. (넌 무슨 os를 사용하는것이냐?)")
        }
    }
}