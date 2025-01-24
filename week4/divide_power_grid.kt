import kotlin.math.abs

class Solution {
    fun getDisconnectTowers(wire: IntArray, wires: Array<IntArray>, compareNum: Int): Int {
        var answer = 1
        wires.forEach {
            if (!wire.contentEquals(it) && (compareNum == it[0] || compareNum == it[1]))
                answer += getDisconnectTowers(it, wires, if (compareNum == it[0]) it[1] else it[0])
        }
        return answer
    }
    fun solution(n: Int, wires: Array<IntArray>): Int {
        var bestNumOfTower: Int = -1
        wires.forEach {
            val value = getDisconnectTowers(it, wires, it[0])
            if (abs(value - n/2) < abs(bestNumOfTower - n/2))
                bestNumOfTower = value
            if (bestNumOfTower == n/2)
                return if (n % 2 == 0) 0 else 1
        }
        return abs(n-bestNumOfTower - bestNumOfTower)
    }
}