package com.example.mvctutorial.designpattern._04factory

import com.example.mvctutorial.designpattern._04factory.fw.Item

class DefaultItem(private val itemName: String) : Item() {


    override fun use() {
        println("$itemName 사용했습니다!")
    }


}