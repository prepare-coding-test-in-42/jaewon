import java.util.ArrayDeque

class Solution {
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        var answer = intArrayOf()
        var queue = ArrayDeque(progresses.asList())
        val modifiedSpeeds = speeds.toMutableList()
        while (queue.firstOrNull() != null) {
            queue = ArrayDeque(queue.mapIndexed { i, progress ->
                progress + modifiedSpeeds[i]
            })
            var deploys = 0
            while (queue.firstOrNull() != null && queue.firstOrNull()!! >= 100) {
                deploys++
                queue.pollFirst()
                modifiedSpeeds.removeFirst()
            }
            deploys.takeIf { it != 0 }?.let {
                answer += deploys
            }
        }
        return answer
    }
}