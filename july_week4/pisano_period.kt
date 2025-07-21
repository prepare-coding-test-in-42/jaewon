package com.example.algorithm

fun main() {
    val p = readln().toInt()
    repeat(p) {
        val (n, m) = readln().split(" ").map { it.toInt() }

        var km = 3
        var f1 = 1 % m
        var f2 = 1 % m
        var f3 = (f1 + f2) % m

        while(f2 != 1 || f3 != 0) {
            f1 = f2 % m
            f2 = f3 % m
            f3 = (f1 + f2) % m
            km++
        }

        println("$n $km")
    }
}