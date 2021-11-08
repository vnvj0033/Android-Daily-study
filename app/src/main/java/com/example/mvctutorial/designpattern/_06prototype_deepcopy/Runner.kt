package com.example.mvctutorial.designpattern._06prototype_deepcopy

// Cloneable은 객체의 기본형만 복사하고 객체속성의 값은 주소값을 복사한다. 따라서 깊은 복사룰 실시하려면 clone에서 객체를 새로 만들어 할당해 준다.
fun main() {
    val navi = Cat(
        "navi",
        Age(1)
    )
    val meyu = navi.clone()

    meyu.name = "meyu"
    meyu.age.value = 3

    println("${navi.name}, ${navi.age.value}")
    println("${meyu.name}, ${meyu.age.value}")
}
