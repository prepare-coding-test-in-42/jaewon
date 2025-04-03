import kotlin.math.*

fun main() {
    val geers = Array (4) {
        readln().map { it.digitToInt() }
    }
    val orders = Array (readln().toInt()) {
        readln().split(" ").map { it.toInt() }
    }

    fun rotate(geer: List<Int>, clockwise: Int): List<Int> {
        val init = geer[0]
        return geer.mapIndexed { i, it ->
            val next = if (clockwise == 1) i-1 else i+1
            if (next in geer.indices)
                geer[next]
            else if (next == 8)
                init
            else
                geer[7]
        }
    }

    orders.forEach { order ->
        val interlocks = BooleanArray(3) {
            geers[it][2] != geers[it + 1][6]
        }
        val rotateds = BooleanArray(geers.size)
        fun chainRotate(geerIdx: Int, clockwise: Int) {
            geers[geerIdx] = rotate(geers[geerIdx], clockwise)
            rotateds[geerIdx] = true
            if (geerIdx in interlocks.indices && interlocks[geerIdx] && !rotateds[geerIdx + 1])
                chainRotate(geerIdx + 1, -clockwise)
            if (geerIdx - 1 in interlocks.indices && interlocks[geerIdx - 1] && !rotateds[geerIdx - 1])
                chainRotate(geerIdx - 1, -clockwise)
        }
        chainRotate(order[0] - 1, order[1])
    }
    println (
        geers.mapIndexed { i, it ->
            if (it[0] != 0)
                2.toDouble().pow(i)
            else
                0.toDouble()
        }.sum().toInt()
    )
}