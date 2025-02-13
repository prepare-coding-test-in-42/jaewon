class Solution {
    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
        val sortedLost = lost.filter { !reserve.contains(it) }.sorted()
        val sortedReserve = reserve.filter { !lost.contains(it) }.sorted().toMutableList()

        var answer = n - sortedLost.size
        sortedLost.forEach {
            run {
                sortedReserve.forEach { r ->
                    if (it - 1 == r || it + 1 == r) {
                        sortedReserve.remove(r)
                        answer++
                        return@run
                    }
                }
            }
        }
        return answer
    }
}