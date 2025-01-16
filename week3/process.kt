import java.util.ArrayDeque

class Solution {
    fun solution(priorities: IntArray, location: Int): Int {
        var answer = 0
        val queue = ArrayDeque(
            List(priorities.size) {
                priorities[it] to it
            }
        )
        while (queue.isNotEmpty()) {
            val front = queue.pollFirst()
            if (queue.isEmpty())
                return priorities.size
            if (front.first < queue.maxOf { it.first })
                queue.offer(front)
            else if (front.second == location)
                return answer + 1
            else
                answer++
        }
        return answer
    }
}