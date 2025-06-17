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
        MutableList<Pair<Int, MutableList<Int>>>(n) { 5 to mutableListOf<Int>() }
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
        for (i in ages.indices) {
            if (nr - ages[i] < 0) {
                ages[i] *= -1
                continue
            }
            nr -= ages[i]
            ages[i]++
        }
        return nr to ages
    }
    fun summer(nour: Int, ages: MutableList<Int>): Pair<Int, MutableList<Int>> {
        var nr = nour
        val alive = mutableListOf<Int>()
        for (age in ages) {
            if (age < 0)
                nr += age * -1 / 2
            else
                alive.add(age)
        }
        return nr to alive
    }

    fun fall(i: Int, j: Int, ages: MutableList<Int>) {
        val drdcs = listOf(-1 to -1, -1 to 0, -1 to +1, 0 to -1, 0 to +1, +1 to -1, +1 to 0, +1 to +1)
        ages.forEach {
            if (it % 5 == 0) {
                drdcs.forEach { (dr, dc) ->
                    val adjr = i + dr
                    val adjc = j + dc
                    if (adjr in map.indices && adjc in map.indices)
                        map[adjr][adjc].second.add(0, 1)
                }
            }
        }
    }
    repeat(k) {
        for (i in map.indices) {
            for (j in map[i].indices) {
                map[i][j] = spring(map[i][j].first, map[i][j].second)
            }
        }
        for (i in map.indices) {
            for (j in map[i].indices) {
                map[i][j] = summer(map[i][j].first, map[i][j].second)
            }
        }
        for (i in map.indices) {
            for (j in map[i].indices) {
                fall(i, j, map[i][j].second)
            }
        }
        for (i in map.indices) {
            for (j in map[i].indices) {
                map[i][j] = map[i][j].first + nourishment[i][j] to map[i][j].second
            }
        }
    }
    print(
        map.sumOf {
            it.sumOf {
                it.second.size
            }
        }
    )
}