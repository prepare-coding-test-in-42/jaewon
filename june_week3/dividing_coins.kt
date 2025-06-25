package com.example.algorithm

fun main() {
    repeat(3) {
        val n = readln().toInt()
        val coins = Array(n) {
            readln().split(" ").map { it.toInt() }
        }
        
        val total = coins.sumOf { (value, count) -> value * count }
        println(
            if (total % 2 == 0) {
                val target = total / 2
                canDivideEqually(coins, target)
            } else {
                0
            }
        )
    }
}

fun canDivideEqually(coins: Array<List<Int>>, target: Int): Int {
    val dp = Array(coins.size + 1) { BooleanArray(target + 1) }
    dp[0][0] = true
    
    for (i in coins.indices) {
        val (value, count) = coins[i]
        for (j in 0..target) {
            if (dp[i][j]) {
                for (k in 0..count) {
                    val next = j + value * k
                    if (next <= target)
                        dp[i + 1][next] = true
                }
            }
        }
    }

    return if (dp[coins.size][target]) 1 else 0
}