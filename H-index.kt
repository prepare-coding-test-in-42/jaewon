class Solution {
    fun solution(citations: IntArray): Int {
        var answer = 0
        val sorted = citations.sortedDescending()
        sorted.forEachIndexed { i, c ->
            if (c >= i + 1) {
                answer = i + 1
            }
        }
        return answer
    }
}