package com.example.mvctutorial.designpattern._14facade_pattern.system

class Facade {
    val system01 = HelpSystem01()
    val system02 = HelpSystem02()
    val system03 = HelpSystem03()

    fun process() {
        system01.process()
        system02.process()
        system03.process()
    }
}