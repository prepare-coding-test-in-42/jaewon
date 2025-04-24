package com.example.algorithm

fun main() {
    val (n, m, k) = readln().split(" ").map { it.toInt() }
    val nourishment = Array(n) {
        readln().split(" ").map { it.toInt() }
    }
    val trees = Array((m)) {
        readln().split(" ").map { it.toInt() - 1}
    }
    var map = List(n) {
        List<Pair<Int, MutableList<Int>>>(n) { 5 to mutableListOf<Int>() }
    }
    trees.forEach { (x, y, age) ->
        map[x][y].second.add(age + 1)
    }
    map.forEach {
        it.forEach {
            it.second.sort()
        }
    }

    fun spring(nour: Int, ages: MutableList<Int>): Pair<Int, MutableList<Int>> {
        var nr = nour
        var a = ages
        for (i in a.indices) {
            if (nr - a[i] < 0) {
                a[i] *= -1
                continue
            }
            nr -= a[i]
            a[i]++
        }
        return nr to a
    }
    fun summer(nour: Int, ages: MutableList<Int>): Pair<Int, MutableList<Int>> {
        var nr = nour
        ages.filter { it < 0 }.forEach {
            nr += it * -1 / 2
        }
        return nr to ages.filter { it >= 0 }.toMutableList()
    }
    fun fall(i: Int, j: Int, ages: MutableList<Int>) {
        val drdcs = listOf(-1 to -1, -1 to 0, -1 to +1, 0 to -1, 0 to +1, +1 to -1, +1 to 0, +1 to +1)
        ages.forEach {
            if (it % 5 == 0) {
                drdcs.forEach { (dr, dc) ->
                    val adjr = i + dr
                    val adjc = j + dc
                    if (adjr in map.indices && adjc in map.indices)
                        map[adjr][adjc].second.add(1)
                }
            }
        }
    }
    repeat(k) {
        map = map.map {
            it.map {
                spring(it.first, it.second)
            }
        }
        map = map.map {
            it.map {
                summer(it.first, it.second)
            }
        }
        map.forEachIndexed { i, it ->
            it.forEachIndexed { j, jt ->
                fall(i, j, jt.second)
            }
        }
        map = map.mapIndexed { i, it ->
            it.mapIndexed { j, jt ->
                jt.first + nourishment[i][j] to jt.second
            }
        }
    }
    print(
        map.sumOf {
            it.sumOf {
                it.second.count { it >= 0}
            }
        }
    )
}