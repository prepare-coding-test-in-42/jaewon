import java.util.ArrayDeque

class Solution {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        var answer = 0
        val throughBridges = mutableListOf<Int>()
        val waitingQueue = ArrayDeque(truck_weights.asList())
        val crossingQueue = ArrayDeque<Pair<Int, Int>>()
        while (throughBridges != truck_weights.asList()) {
            answer++
            if (crossingQueue.isNotEmpty() && answer - crossingQueue.firstOrNull()!!.second == bridge_length)
                throughBridges.add(crossingQueue.pollFirst().first)
            if (waitingQueue.isNotEmpty() && crossingQueue.sumOf { it.first } + waitingQueue.firstOrNull()!! <= weight)
                crossingQueue.offer(waitingQueue.pollFirst() to answer)
        }
        return answer
    }
}