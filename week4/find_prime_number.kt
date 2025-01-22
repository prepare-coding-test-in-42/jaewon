import kotlin.math.sqrt

class Solution {
    fun getAllNums(curr: String, remain: String, allNums: MutableList<Int>) {
        if (curr.isNotEmpty())
            allNums.add(curr.toInt())
        remain.forEachIndexed { i, r ->
            getAllNums(curr + r, remain.substring(0, i) + remain.substring(i + 1), allNums)
        }
    }
    fun solution(numbers: String): Int {
        val allNums = mutableListOf<Int>()
        getAllNums("", numbers, allNums)
        return allNums.distinct().filter { num ->
            if (num <= 1) false
            else (2..sqrt(num.toDouble()).toInt()).all { num % it != 0 }
        }.size
    }
}