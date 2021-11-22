package com.example.mvctutorial.designpattern._03template

class ConcreteConnectHelper: AbstConnectHelper() {
    override fun doSecurity(info: String) = info

    override fun authentication(id: String, password: String): String {
        return if ((id == "abc") or (password == "abc")) "true info" else "false info"
    }

    override fun authorization(userName: String): Int = userName.length

    override fun connection(info: String): String = info
}