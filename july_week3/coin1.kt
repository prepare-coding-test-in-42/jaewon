package com.example.algorithm

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val coins = Array(n) { readln().toInt() }
    val dp = IntArray(k + 1)
    dp[0] = 1

    for (coin in coins) {
        for (i in coin..k) {
            dp[i] += dp[i - coin]
        }
    }
    println(dp[k])
}