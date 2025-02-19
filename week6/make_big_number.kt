class Solution {
    fun solution(number: String, k: Int): String {
        val stack = ArrayDeque<Char>()

        var removeCount = 0
        number.forEach {
            while (removeCount < k
                && stack.isNotEmpty()
                && stack.last() < it) {
                stack.removeLast()
                removeCount++
            }
            stack.addLast(it)
        }
        while (removeCount < k) {
            stack.removeLast()
            removeCount++
        }

        return stack.joinToString("")
    }
}