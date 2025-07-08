package com.example.algorithm

fun main() {
    val n = readln().toInt()
    val m = readln().toInt()
    val vips = if (m > 0) {
        Array(m) { readln().toInt() }
    } else {
        emptyArray()
    }

    val dp = LongArray(n + 1)
    dp[1] = 1L
    if (n >= 2) {
        dp[2] = 2L
    }
    for (i in 3..n) {
        dp[i] = dp[i-1] + dp[i-2]
    }
    val seats = BooleanArray(n)
    vips.forEach { vip ->
        if (m == 0) println("wtf")
        seats[vip - 1] = true
    }

    var answer = 1L
    var len = 0
    for (i in seats.indices) {
        if (seats[i]) {
            if (len > 0)
                answer *= dp[len]
            len = 0
        } else {
            len++
        }
    }
    if (len > 0)
        answer *= dp[len]

    println(answer)
}