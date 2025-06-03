package com.example.algorithm

fun main() {
    val n = readln().toInt()
    val eggs = Array(n) {
        readln().split(" ").map { it.toInt() }.toTypedArray()
    }

    fun attack(attacker: Int, currEggs: Array<Array<Int>>): Int {
        var maxBroken = currEggs.filter { it[0] <= 0 }.size
        if (attacker >= n) {
            return maxBroken
        }
        if (currEggs[attacker][0] <= 0) {
            return attack(attacker + 1, currEggs)
        }
        for (defender in currEggs.indices) {
            if (defender == attacker || currEggs[defender][0] <= 0)
                continue
            val nextEggs = currEggs.map{ it.clone() }.toTypedArray()
            nextEggs[attacker][0] -= nextEggs[defender][1]
            nextEggs[defender][0] -= nextEggs[attacker][1]
            maxBroken = maxOf(maxBroken, attack(attacker + 1, nextEggs))
        }
        return maxBroken
    }
    print(attack(0, eggs))
}