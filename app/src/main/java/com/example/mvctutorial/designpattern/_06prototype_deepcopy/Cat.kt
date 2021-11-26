package com.example.mvctutorial.designpattern._06prototype_deepcopy

class Cat(var name: String, var age: Age) : Cloneable {

    public override fun clone(): Cat {

        val cat = super.clone() as Cat
        cat.age = Age(age.value)

        return cat
    }
}