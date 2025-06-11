package com.example.algorithm

fun main() {
    val (n, q) = readln().split(" ").map { it.toInt() }
    val usados = Array(n-1) {
        readln().split(" ").map { it.toInt() }
    }
    val questions = Array(q) {
        readln().split(" ").map { it.toInt() }
    }

    val graph = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }
    usados.forEach { (p, q, r) ->
        graph[p].add(Pair(q, r))
        graph[q].add(Pair(p, r))
    }

    questions.forEach { (k, v) ->
        var answer = 0
        val visited = BooleanArray(n + 1)
        val queue = ArrayDeque<Int>()
        queue.addLast(v)
        visited[v] = true
        
        while (queue.isNotEmpty()) {
            val front = queue.first()
            graph[front].forEach { (next, usado) ->
                if (!visited[next]) {
                    visited[next] = true
                    if (usado >= k) {
                        answer++
                        queue.addLast(next)
                    }
                }
            }
        }
        
        println(answer)
    }
}