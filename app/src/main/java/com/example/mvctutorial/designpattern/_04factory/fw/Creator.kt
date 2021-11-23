package com.example.mvctutorial.designpattern._04factory.fw

abstract class Creator {

    open fun create(itemName: String): Item {
        init(itemName)
        val item = createItem(itemName)
        end(itemName)

        return item
    }

    protected abstract fun end(itemName: String): String
    protected abstract fun init(itemName: String): String
    protected abstract fun createItem(itemName: String): Item
}
