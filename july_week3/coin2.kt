package com.example.algorithm

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val coins = Array(n) { readln().toInt() }
    val dp = IntArray(k + 1) { k + 1 }
    dp[0] = 0

    for (coin in coins) {
        for (i in coin..k) {
            dp[i] = minOf(dp[i], dp[i - coin] + 1)
        }
    }

    print(if (dp[k] == k + 1) -1 else dp[k])
}