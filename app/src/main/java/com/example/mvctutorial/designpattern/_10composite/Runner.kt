package com.example.mvctutorial.designpattern._10composite

import com.example.mvctutorial.designpattern._10composite.java.Component
import com.example.mvctutorial.designpattern._10composite.java.File
import com.example.mvctutorial.designpattern._10composite.java.Folder

fun main() {
    val root =
        Folder("root")
    val home =
        Folder("home")
    val garam =
        Folder("garam")
    val music =
        Folder("music")
    val picture =
        Folder("picture")
    val doc =
        Folder("doc")
    val usr =
        Folder("usr")

    val track1 =
        File("track1")
    val track2 =
        File("track2")
    val pic1 =
        File("pic1")
    val doc1 =
        File("doc1")
    val java =
        File("java")

    root.addComponent(home)
        home.addComponent(garam)
            garam.addComponent(music)
                music.addComponent(track1)
                music.addComponent(track2)
            garam.addComponent(picture)
                picture.addComponent(pic1)
            garam.addComponent(doc)
                doc.addComponent(doc1)

    root.addComponent(usr)
        usr.addComponent(java)

    show(root, 0)


}

private fun show(component: Component, i: Int) {
    println(i.toString() + " : " + component.javaClass.simpleName + "|" + component.name)
    if (component is Folder) {
        for (c in component.children) {
            show(c, i+1)
        }
    }
}
