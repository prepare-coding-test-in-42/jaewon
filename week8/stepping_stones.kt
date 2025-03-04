class Solution {
    fun solution(distance: Int, rocks: IntArray, n: Int): Int {
        var answer = 0
        val sortedRocks = (rocks + distance).sorted()
        var start = 0
        var end = distance
        while (start <= end) {
            val mid = (start + end) / 2
            var rockRemove = 0
            var curr = 0
            sortedRocks.forEach {
                if (it - curr < mid)
                    rockRemove++
                else
                    curr = it
            }
            if (n < rockRemove) {
                end = mid - 1
            } else {
                answer = mid
                start = mid + 1
            }
        }
        return answer
    }
}