package com.example.algorithm

fun main() {
    val t = readln().toInt()
    
    fun d(n: Int): Int {
        return 2*n % 10000
    }
    fun s(n: Int): Int {
        return if (n == 0) 9999 else n-1
    }
    fun l(n: Int): Int {
        return (n % 1000) * 10 + (n / 1000)
    }
    fun r(n: Int): Int {
        return (n % 10) * 1000 + (n / 10)
    }
    
    repeat(t) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        val q = ArrayDeque<Pair<Int, String>>()
        val visited = BooleanArray(10000)
        
        q.addLast(a to "")
        visited[a] = true
        
        while (q.isNotEmpty()) {
            val (current, path) = q.removeFirst()
            if (current == b) {
                println(path)
                break
            }
            
            val d = d(current)
            if (!visited[d]) {
                visited[d] = true
                q.addLast(d to path+"D")
            }
            val s = s(current)
            if (!visited[s]) {
                visited[s] = true
                q.addLast(s to path+"S")
            }
            val l = l(current)
            if (!visited[l]) {
                visited[l] = true
                q.addLast(l to path+"L")
            }
            val r = r(current)
            if (!visited[r]) {
                visited[r] = true
                q.addLast(r to path+"R")
            }
        }
    }
}