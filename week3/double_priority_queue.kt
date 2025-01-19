class Solution {
    fun solution(operations: Array<String>): IntArray {
        val queue = mutableListOf<Int>()
        operations.forEach {
            val list = it.split(" ")
            when {
                list[0] == "I" -> queue.add(list[1].toInt())
                else -> {
                    if (list[1].toInt() == 1)  queue.remove(queue.maxOrNull())
                    else queue.remove(queue.minOrNull())
                }
            }
        }
        return intArrayOf(queue.maxOrNull()?:0, queue.minOrNull()?:0)
    }
}