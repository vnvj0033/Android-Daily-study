package com.example.mvctutorial.di.di_pre.ex4namedannotation

fun main() {
    val personComponent: SchoolComponent = DaggerSchoolComponent.create()

    val student = Student()
    personComponent.injectStudent(student)
    println(student.name)

    val teacher = Teacher()
    personComponent.injectTeacher(teacher)
    println(teacher.name) // TeacherB
}
