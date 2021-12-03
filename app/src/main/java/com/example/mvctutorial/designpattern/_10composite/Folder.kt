package com.example.mvctutorial.designpattern._10composite

import java.util.ArrayList

class Folder(name: String): Component(name) {

    private var child = ArrayList<Component>()

    fun addComponent(component: Component): Boolean{
        return child.add(component)
    }

    fun getChildren(): List<Component> {
        return child
    }
}
