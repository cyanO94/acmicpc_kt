package implementation.g

import kotlin.math.max

fun main() {
    Solution1987().solve()
}
class Solution1987 {
    lateinit var board: List<IntArray>
    val history = IntArray(26) { 0 }

    val dx = listOf(0, 0, 1, -1)
    val dy = listOf(1, -1, 0, 0)

    var r = 0
    var c = 0
    var maxDepth = 0
    var depth = 0

    fun solve() {
        val (r, c) = readln().split(" ").map { it.toInt() }
        this.r = r
        this.c = c
        board = List<IntArray>(r) { readln().map { it.code - 'A'.code }.toIntArray() }

        dfs(0, 0)

        println(maxDepth)
    }

    fun dfs(x: Int, y:Int) {
        history[board[x][y]] += 1
        depth += 1

        var cnt = 0

        for (i in 0..3) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (nx < 0 || ny < 0 || nx >= r || ny >= c) continue
            if (history[board[nx][ny]] > 0) continue
            cnt += 1
            dfs(nx, ny)
        }

        if (cnt == 0) {
            maxDepth = max(maxDepth, depth)
        }
        depth -= 1
        history[board[x][y]] -= 1
    }
}