class Solution {
    fun solution(tickets: Array<Array<String>>): List<String> {
        var visiteds = arrayOf<Array<Int>>()
        val newTickets = arrayOf(arrayOf("", "ICN")) + tickets
        val queue = ArrayDeque <Pair<Int, Array<Int>>>()
        queue.addLast(0 to arrayOf(0))
        while (queue.isNotEmpty()) {
            val (index, visited) = queue.removeFirst()
            if (visited.size == newTickets.size)
                visiteds += visited
            newTickets.indices.filter { !visited.contains(it) }.forEach {
                if (newTickets[index][1] == newTickets[it][0]) {
                    queue.addLast(it to visited + arrayOf(it))
                }
            }
        }
        return visiteds.map { it.map { i -> newTickets[i] } }.sortedBy {
            it.toTypedArray().flatten().joinToString("")
        }.first().drop(1).mapIndexed { i, ticket ->
            when (i) {
                tickets.lastIndex -> ticket.toList()
                else -> listOf(ticket[0])
            }
        }.flatten()
    }
}