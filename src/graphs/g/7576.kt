package graphs.g
import java.util.LinkedList
import java.util.Queue

fun main() {
    Solution7576().solve()
}

class Solution7576 {
    val dx = listOf(-1, 0, 1, 0)
    val dy = listOf(0, 1, 0, -1)

    fun solve() {
        val (c, r) = readln().split(" ").map { it.toInt() }
        val q = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>
        val nq = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>
        val visited = List<BooleanArray>(r) { BooleanArray(c) { false } }
        var emptyCnt = 0
        var tomatoCnt = 0
        var round = 0
        val board = List<IntArray>(r) { curR ->  readln().split(" ").mapIndexed { idx, it->
            val curC = it.toInt()
            if (curC == 1) {
                q.add(Pair(curR, idx))
                tomatoCnt += 1
            }
            if (curC == -1) emptyCnt += 1
            curC
        }.toIntArray() }

        if (tomatoCnt + emptyCnt == r * c) {
            println(0)
            return
        }

        while (q.isNotEmpty()) {
            val node = q.poll()
            if (board[node.first][node.second] == 0) {
                board[node.first][node.second] = 1
                tomatoCnt += 1
            }

            repeat(4) {
                val nx = node.first + dx[it]
                val ny = node.second + dy[it]
                if (nx < 0 || ny < 0 || nx >= r || ny >= c) return@repeat
                if (visited[nx][ny]) return@repeat
                if (board[nx][ny] == 0) {
                    visited[nx][ny] = true
                    nq.add(Pair(nx, ny))
                }
            }

            if (q.isEmpty()) {
                while (nq.isNotEmpty()) q.add(nq.poll())
                round += 1
            }
        }

        if (tomatoCnt + emptyCnt == r * c) {
            println(round - 1)
        } else {
            println(-1)
        }
    }
}