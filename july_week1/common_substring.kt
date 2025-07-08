package com.example.algorithm

fun main() {
    val a = readln()
    val b = readln()
    
    val n = a.length
    val m = b.length
    
    val dp = Array(n + 1) { IntArray(m + 1) }
    var answer = 0
    for (i in 1..n) {
        for (j in 1..m) {
            if (a[i - 1] == b[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1
                answer = maxOf(answer, dp[i][j])
            }
        }
    }
    println(answer)
}