package com.example.mvctutorial.designpattern._21command

interface Command : Comparable<Command>{

    fun execute()
}