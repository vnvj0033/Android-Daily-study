package com.example.mvctutorial.designpattern._06prototype_deepcopy;

import androidx.annotation.NonNull;

public class Cat implements Cloneable{

    String name;
    Age age;

    Cat(String name, Age age){
        this.name = name;
        this.age = age;
    }

    @NonNull
    @Override
    protected Cat clone() throws CloneNotSupportedException {

        Cat cat = (Cat) super.clone();

        cat.age = new Age(age.value);


        return cat;
    }
}
