package com.example.mvctutorial.designpattern._21command

class StringCommandPrint(val string: String) : Command {
    override fun execute() {
        println(string)
    }

    override fun compareTo(other: Command): Int {
        return string.length - (other as StringCommandPrint).string.length
    }
}