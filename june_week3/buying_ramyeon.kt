package com.example.algorithm

fun main() {
    val (n, b, c) = readln().split(" ").map { it.toInt() }
    val arr = readln().split(" ").map { it.toInt() }.toTypedArray()
    var answer = 0L
    
    if (b <= c) {
        println(arr.sumOf { it.toLong() } * b)
        return
    }

    for (i in 0 until n) {
        if (i + 2 < n) {
            if (arr[i + 1] > arr[i + 2]) {
                val count = minOf(arr[i], arr[i + 1] - arr[i + 2])
                arr[i] -= count
                arr[i + 1] -= count
                answer += count.toLong() * (b + c)
            }
            
            val count = minOf(arr[i], arr[i + 1], arr[i + 2])
            arr[i] -= count
            arr[i + 1] -= count
            arr[i + 2] -= count
            answer += count.toLong() * (b + 2 * c)
        }
        
        if (i + 1 < n) {
            val count = minOf(arr[i], arr[i + 1])
            arr[i] -= count
            arr[i + 1] -= count
            answer += count.toLong() * (b + c)
        }
        
        val count = arr[i]
        arr[i] -= count
        answer += count * b
    }
    println(answer)
}