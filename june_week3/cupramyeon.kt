package com.example.algorithm
import java.util.*
import kotlin.math.pow

fun main() {
    val n = readln().toInt()
    val priorityQuiz = PriorityQueue<List<Long>> { a, b ->
        when {
            a[0] != b[0] -> a[0].compareTo(b[0])
            else -> b[1].compareTo(a[1])
        }
    }
    repeat(n) {
        priorityQuiz.add(readln().split(" ").map { it.toLong() })
    }
    var answer = 0L
    var curr = 0
    var prevCup = 2.0.pow(31).toLong()
    while (priorityQuiz.isNotEmpty()) {
        val (dead, cup) = priorityQuiz.poll()!!
        if (dead > curr) {
            curr++
            answer += cup
            if (priorityQuiz.isNotEmpty()) {
                val (nextDead, nextCup) = priorityQuiz.peek()!!
                if (nextDead <= curr && prevCup < nextCup) {
                    answer -= prevCup
                    curr--
                }
            }
            prevCup = cup
        }
    }
    println(answer)
}