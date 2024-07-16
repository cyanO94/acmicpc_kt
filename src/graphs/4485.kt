package graphs

fun main() {
    val solution = Solution4485()
    solution.solve()
}


class Solution4485 {
    val MAX_N = 126 * 9
    fun solve() {
        var pNum = 1
        while (true) {
            val cnt = readln().toInt()
            if (cnt == 0) return

            val res = unitSolve(cnt)
            println("Problem $pNum: $res")
            pNum += 1
        }
    }

    data class Cost(val x: Int, val y: Int, var curCost: Int)
    val dx = listOf(0, 0, 1, -1)
    val dy = listOf(1, -1, 0, 0)

    fun unitSolve(cnt: Int): Int {
        val board = mutableListOf<List<Int>>()
        repeat(cnt) {
            board.add(readln().split(" ").map { it.toInt() })
        }

        val costBoard = board.map { it.map { MAX_N }.toMutableList() }
        costBoard[0][0] = board[0][0]

        val q = mutableListOf<Cost>()
        q.add(Cost(0, 0, board[0][0]))

        while (!q.isEmpty()) {
            val cost = q.removeFirst()

            for (i in 0 until 4) {
                val nx = cost.x + dx[i]
                val ny = cost.y + dy[i]

                if (nx < 0 || ny < 0 || nx >= cnt || ny >= cnt) continue
                if (board[nx][ny] + costBoard[cost.x][cost.y] < costBoard[nx][ny]) {
                    costBoard[nx][ny] = board[nx][ny] + costBoard[cost.x][cost.y]
                    q.add(Cost(nx, ny, board[nx][ny] + costBoard[cost.x][cost.y]))
                }
            }
        }

        return costBoard[cnt - 1][cnt - 1]
    }
}