class Solution {
    val sequence = "AEIOU"
    val vowelDict = mutableListOf<String>()

    fun getList(curr: String) {
        if (curr.length == 6) return
        vowelDict.add(curr)
        sequence.forEach {
            getList(curr + it)
        }
    }
    fun solution(word: String): Int {
        getList("")
        return vowelDict.indexOf(word)
    }
}