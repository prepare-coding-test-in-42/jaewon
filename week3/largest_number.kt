class Solution {
    fun solution(numbers: IntArray): String {
        return numbers.sortedWith { a, b ->
            "$b$a".compareTo("$a$b")
        }.joinToString("")
        .takeIf {
            it.any { num -> num != '0'}
        } ?: "0"
    }
}