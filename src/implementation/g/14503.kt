package implementation.g

import java.util.LinkedList
import java.util.Queue

fun main() {
    val solution = Solution14503()
    solution.solve()
}

class Solution14503 {
    // 북동남서
    val dx = intArrayOf(-1, 0, 1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    fun solve() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val (r, c, d) = readln().split(" ").map { it.toInt() }
        val board = Array(n) { readln().split(" ").map{ it.toInt() }.toMutableList() }

        val q: Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()
        var direction = d
        q.add(Pair(r, c))

        var cnt = 0
        var canGoN = true
        while (!q.isEmpty()) {
            val node = q.poll()
            val x = node.first
            val y = node.second

            if (board[x][y] == 0) cnt += 1
            board[x][y] = 3

            canGoN = false
            for (i in 0..3) {
                var nd = direction - i - 1
                if (nd < 0) nd = 4 + nd

                val nx = x + dx[nd]
                val ny = y + dy[nd]

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue
                if (board[nx][ny] == 0) {
                    canGoN = true
                    direction = nd
                    q.add(Pair(nx, ny))
                    break
                }
            }

            // back
            // 0 - 2  1 - 3
            if (!canGoN) {
                val nd =
                    if (direction == 0) 2
                    else if (direction == 2) 0
                    else if (direction == 1) 3
                    else 1

                val nx = x + dx[nd]
                val ny = y + dy[nd]

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) break

                if (board[nx][ny] != 1) q.add(Pair(nx, ny))
            }
        }
        println("$cnt")
    }
}