class Solution {
    fun solution(n: Int, times: IntArray): Long {
        var answer: Long = 0
        var start: Long = 1
        var end: Long = 1_000_000_000_000_000_000
        while (start <= end) {
            val mid = (start + end) / 2
            var enterPeople: Long = 0
            times.forEach { enterPeople += mid / it }
            if (n <= enterPeople) {
                answer = mid
                end = mid - 1
            }
            else
                start = mid + 1
        }
        return answer
    }
}