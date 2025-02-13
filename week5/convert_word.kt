class Solution {
    fun solution(begin: String, target: String, words: Array<String>): Int {
        if (!words.contains(target))
            return 0
        val newWords = arrayOf(begin) + words
        val visited = BooleanArray(newWords.size)
        fun isAdj(word: String, adjWord:String): Boolean {
            return word.mapIndexed { i, c ->
                if (c != adjWord[i]) 1 else 0
            }.sum() == 1
        }
//         bfs
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.addLast(0 to 0)
        visited[0] = true
        while (queue.isNotEmpty()) {
            val (front, depth) = queue.removeFirst()
            if (newWords[front] == target) {
                return depth
            }
            visited.forEachIndexed { i, v ->
                if (!v && isAdj(newWords[front], newWords[i])) {
                    queue.addLast(i to depth + 1)
                    visited[i] = true
                }
            }
        }
        return -1
    }
}