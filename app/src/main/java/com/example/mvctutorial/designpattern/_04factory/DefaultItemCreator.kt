package com.example.mvctutorial.designpattern._04factory

import com.example.mvctutorial.designpattern._04factory.fw.Creator
import com.example.mvctutorial.designpattern._04factory.fw.Item

class DefaultItemCreator : Creator() {

    override fun end(itemName: String): String {
        println("Default 마무리 작업!")
        return itemName
    }

    override fun init(itemName: String): String {
        println("Default 초기 작업!")
        return itemName
    }

    override fun createItem(itemName: String): Item {
        return DefaultItem(itemName)
    }
}