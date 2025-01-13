class Solution {
    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        var answer = mutableListOf<Int>()
        // 필요정보 : 종류, 인덱스, 값
        val map = mutableMapOf<String, MutableMap<Int, Int>>()
        for (i in plays.indices) {
            if (map[genres[i]].isNullOrEmpty()) {
                map[genres[i]] = mutableMapOf(i to plays[i])
            } else {
                map[genres[i]]!![i] = plays[i]
            }
        }
        val mapToSortedList = map.toList().sortedByDescending { it.second.values.sum() }
        // [(pop, {1=600, 4=2500}), (classic, {0=500, 2=150, 3=800})]
        mapToSortedList.forEach {
            val playList = it.second.toList().sortedByDescending { play -> play.second }.take(2)
            answer.addAll(playList.map { it.first })
        }
        return answer.toIntArray()
    }
}
