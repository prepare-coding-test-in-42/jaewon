package com.example.algorithm

fun main() {
    val n = readln().toInt()
    val map = Array(n) {
        readln().split(" ").map { it.toInt() }.toTypedArray()
    }

    val maxDP = Array(n) {
        IntArray(3)
    }
    val minDP = Array(n) {
        Array(3) { Int.MAX_VALUE }
    }
    for (i in 0..2) {
        maxDP[0][i] = map[0][i]
        minDP[0][i] = map[0][i]
    }

    for (i in 1 until n) {
        for (j in 0..2) {
            val maxPV = maxDP[i - 1][j]
            val minPV = minDP[i - 1][j]
            when (j) {
                0 -> {
                    maxDP[i][0] = maxOf(maxDP[i][0], maxPV + map[i][0])
                    minDP[i][0] = minOf(minDP[i][0], minPV + map[i][0])
                }
                1 -> {
                    maxDP[i][0] = maxOf(maxDP[i][0], maxPV + map[i][0])
                    maxDP[i][2] = maxOf(maxDP[i][2], maxPV + map[i][2])
                    minDP[i][0] = minOf(minDP[i][0], minPV + map[i][0])
                    minDP[i][2] = minOf(minDP[i][2], minPV + map[i][2])
                }
                2 -> {
                    maxDP[i][2] = maxOf(maxDP[i][2], maxPV + map[i][2])
                    minDP[i][2] = minOf(minDP[i][2], minPV + map[i][2])
                }
            }
            maxDP[i][1] = maxOf(maxDP[i][1], maxPV + map[i][1])
            minDP[i][1] = minOf(minDP[i][1], minPV + map[i][1])
        }
    }
    print("${maxDP[n - 1].max()} ${minDP[n - 1].min()}")
}