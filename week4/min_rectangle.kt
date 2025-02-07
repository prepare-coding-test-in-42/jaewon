class Solution {
    fun solution(sizes: Array<IntArray>): Int {
        return sizes.maxOf { it.maxOrNull()!! } * sizes.maxOf { it.minOrNull()!! }
    }
}