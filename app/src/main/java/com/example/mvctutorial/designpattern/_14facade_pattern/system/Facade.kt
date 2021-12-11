package com.example.mvctutorial.designpattern._14facade_pattern.system

class Facade {
    private val system01 = HelpSystem01()
    private val system02 = HelpSystem02()
    private val system03 = HelpSystem03()

    fun process() {
        system01.process()
        system02.process()
        system03.process()
    }
}