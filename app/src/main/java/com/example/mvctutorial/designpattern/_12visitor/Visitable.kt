package com.example.mvctutorial.designpattern._12visitor

interface Visitable {
    fun accept(vistor: Visitor)
}