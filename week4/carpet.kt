class Solution {
    fun solution(brown: Int, yellow: Int): IntArray {
        return (1..kotlin.math.sqrt(yellow.toDouble()).toInt()).filter {
            yellow % it ==0 && yellow / it + it + 2 == brown / 2
        }.let { intArrayOf(yellow / it.first() + 2, it.first() + 2) }
    }
}