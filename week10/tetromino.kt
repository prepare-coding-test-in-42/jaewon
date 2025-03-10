fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val paper = Array(n) { readln().split(" ").map { it.toInt() } }
    val pieces = arrayOf(
        arrayOf(
            intArrayOf(1,1,1,1),
        ),
        arrayOf(
            intArrayOf(1,1),
            intArrayOf(1,1),
        ),
        arrayOf(
            intArrayOf(1,0),
            intArrayOf(1,0),
            intArrayOf(1,1),

        ),
        arrayOf(
            intArrayOf(1,0),
            intArrayOf(1,1),
            intArrayOf(0,1),

            ),
        arrayOf(
            intArrayOf(1,1,1),
            intArrayOf(0,1,0),
        ),
    )

    fun mirrorHorizontal(piece: Array<IntArray>): Array<IntArray> {
        val row = piece.size
        val col = piece[0].size
        return Array(row) { i ->
            IntArray(col) { j ->
                piece[i][col-1 - j]
            }
        }
    }
    fun mirrorVertical(piece: Array<IntArray>): Array<IntArray> {
        val row = piece.size
        val col = piece[0].size
        return Array(row) { i ->
            IntArray(col) { j ->
                piece[row-1 - i][j]
            }
        }
    }
    fun rotateClockwise(piece: Array<IntArray>): Array<IntArray> {
        val row = piece.size
        val col = piece[0].size
        return mirrorHorizontal(Array(col) { i ->
            IntArray(row) { j ->
                piece[j][i]
            }
        })
    }

    fun addNumInPiece(piece: Array<IntArray>, i: Int, j: Int, it: List<Int>): Int {
        var num = 0
        piece.forEachIndexed { npi, npit ->
            npit.forEachIndexed { npj, npjt ->
                if (npjt == 1 &&
                    i + npi in paper.indices && j + npj in it.indices)
                    num += paper[i + npi][j + npj]
            }
        }
        return num
    }
    var maxNum = 0
    paper.forEachIndexed { i, it ->
        it.indices.forEach { j ->
            pieces.forEach { piece ->
                var newPiece = piece
                repeat(4) { r ->
                    var num = addNumInPiece(newPiece, i, j, it)
                    if (num > maxNum)
                        maxNum = num
                    num = addNumInPiece(mirrorHorizontal(newPiece), i, j, it)
                    if (num > maxNum)
                        maxNum = num
                    num = addNumInPiece(mirrorVertical(newPiece), i, j, it)
                    if (num > maxNum)
                        maxNum = num
                    newPiece = rotateClockwise(newPiece)
                }
            }
        }
    }
    println(maxNum)
}