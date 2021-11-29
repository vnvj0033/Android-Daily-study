package com.example.mvctutorial.designpattern._09bidge


fun main() {
//    val code = PrintMorseCode(SoundMCF())
    val code =
        PrintMorseCode(
            DefaultMCF())

    code.g().a().r().a().m()
}