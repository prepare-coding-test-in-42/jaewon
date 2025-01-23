class Solution {
    fun getCombinations(curr: Array<IntArray>, remain: Array<IntArray>, dungeonCombs: MutableList<Array<IntArray>>) {
        if (curr.isNotEmpty())
            dungeonCombs.add(curr)
        remain.forEachIndexed { i, it ->
            getCombinations(
                curr + it,
                (remain.slice(0 until i) + remain.slice(i+1 until remain.size)).toTypedArray(),
                dungeonCombs
            )
        }
    }
    fun solution(k: Int, dungeons: Array<IntArray>): Int {
        val dungeonCombs = mutableListOf<Array<IntArray>>()
        getCombinations(arrayOf(), dungeons, dungeonCombs)
        var highestCount = 0
        dungeonCombs.forEach { dungeonComb ->
            var count = 0
            var fatigue = k
            run {
                dungeonComb.forEach {
                    if (fatigue >= it[0]) {
                        fatigue -= it[1]
                        if (++count == dungeons.size)
                            return count
                        if (highestCount < count)
                            highestCount = count
                    } else return@run
                }
            }
        }
        return highestCount
    }
}