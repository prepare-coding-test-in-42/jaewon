class Solution {
    fun solution(operations: Array<String>): IntArray {
        val queue = mutableListOf<Int>()
        operations.forEach {
            val (cmd, data) = it.split(" ")
            when (cmd) {
                "I" -> queue.add(data.toInt())
                else -> when (data) {
                    "1" -> queue.remove(queue.maxOrNull())
                    else -> queue.remove(queue.minOrNull())
                }
            }
        }
        return intArrayOf(queue.maxOrNull()?:0, queue.minOrNull()?:0)
    }
}