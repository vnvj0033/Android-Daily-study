package com.example.mvctutorial.designpattern._01strategy

class AObj(private val aInterface: AInterface) {

    fun funcAA() {

        //위임한다.
        aInterface.funcA() //		System.out.println("AAA");
        aInterface.funcA() //		System.out.println("AAA");
        //~ 기능이 필요합니다.
    }
}