package com.example.algorithm

fun main() {
    val (n, m) = readln().split(" ").map{ it.toInt() }
    val map = Array(n) {
        readln().split(" ").map{ it.toInt() }
    }
    val chickens = mutableListOf<Pair<Int, Int>>()
    val houses = mutableListOf<Pair<Int, Int>>()
    map.forEachIndexed { i, it ->
        it.forEachIndexed { j, jt ->
            when (jt) {
                1 -> houses.add(i to j)
                2 -> chickens.add(i to j)
            }
        }
    }
    val selected = BooleanArray(chickens.size)

    fun calcChickenDist(): Int {
        var sum = 0
        houses.forEach {
            val (i, j) = it
            var dist = 2 * n
            selected.forEachIndexed { si, it ->
                val (ci, cj) = chickens[si]
                if(it)
                    dist = minOf(dist, Math.abs(i - ci) + Math.abs(j - cj))
            }
            sum += dist
        }
        return sum
    }
    var answer = 2 * n * 2 * n
    fun backT(idx: Int, depth: Int) {
        if (depth == m) {
            answer = minOf(answer, calcChickenDist())
            return
        }
        for (i in idx until chickens.size) {
            selected[i] = true
            backT(i + 1, depth + 1)
            selected[i] = false
        }
    }
    backT(0, 0)
    print(answer)
}