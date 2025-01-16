import java.util.ArrayDeque

class Solution {
    fun solution(priorities: IntArray, location: Int): Int {
        var answer = 0
        val updatePrior = priorities.toMutableList()
        val queue = ArrayDeque(
            List(priorities.size) {
                priorities[it] to it
            }
        )
        while (queue.firstOrNull() != null) {
            val front = queue.pollFirst()
            val temp = updatePrior.removeFirst()
            if (updatePrior.maxOrNull() == null)
                return priorities.size
            if (front.first < updatePrior.maxOrNull()!!) {
                queue.offer(front)
                updatePrior.add(temp)
            } else if (front.second == location) {
                return answer + 1
            } else {
                answer++
            }
        }
        return answer
    }
}