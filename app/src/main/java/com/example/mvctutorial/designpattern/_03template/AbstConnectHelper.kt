package com.example.mvctutorial.designpattern._03template

abstract class AbstConnectHelper {

    protected abstract fun doSecurity(info: String): String

    protected abstract fun authentication(id: String, password: String): String

    protected abstract fun authorization(userName: String): Int

    protected abstract fun connection(info: String): String

    open fun requestConnection(info: String): String {
        // 암호화된 정보를 복호화 합니다.
        val decodedInfo: String = doSecurity(info)

        // decodedInfo에서 id 와 password를 추출
        val id = "abc"
        val password = "abc"
        val userInfo = authentication(id, password)

        // userInfo에서 userName을 찾아 냅니다.
        val userName = "abc"
        when (authorization(userName)) {
            0 -> {}
            1 -> {}
            2 -> {}
            3 -> {}
            else -> {}
        }
        return connection(userInfo)
    }
}