package com.example.algorithm

fun main() {
    val n = readln().toLong()

    val MOD = 1_000_000L
    fun mul(mat1: Array<LongArray>, mat2: Array<LongArray>): Array<LongArray> {
        val result = Array(2) { LongArray(2) }
        result[0][0] = ((mat1[0][0] * mat2[0][0]) % MOD + (mat1[0][1] * mat2[1][0]) % MOD) % MOD
        result[0][1] = ((mat1[0][0] * mat2[0][1]) % MOD + (mat1[0][1] * mat2[1][1]) % MOD) % MOD
        result[1][0] = ((mat1[1][0] * mat2[0][0]) % MOD + (mat1[1][1] * mat2[1][0]) % MOD) % MOD
        result[1][1] = ((mat1[1][0] * mat2[0][1]) % MOD + (mat1[1][1] * mat2[1][1]) % MOD) % MOD
        return result
    }
    fun pow(mat: Array<LongArray>, exp: Long): Array<LongArray> {
        if (exp == 1L) return mat
        val half = pow(mat, exp / 2)
        val full = mul(half, half)
        return if (exp % 2 == 0L) full else mul(full, mat)
    }

    if (n == 0L) {
        println(0)
        return
    }
    if (n == 1L) {
        println(1)
        return
    }
    val base = arrayOf(longArrayOf(1, 1), longArrayOf(1, 0))
    val result = pow(base, n - 1)
    println(result[0][0] % MOD)
}