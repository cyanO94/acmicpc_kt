package implementation.g


import java.util.LinkedList
import java.util.Queue

fun main() {
    Solution4179().solve()
}

class Solution4179 {
    lateinit var board: MutableList<MutableList<Char>>
    lateinit var initPos: Pair<Int, Int>
    lateinit var firePos: Pair<Int, Int>
    val fq = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>
    val nfq = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>
    val q = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>
    val nq = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>

    var r = 0
    var c = 0
    val dx = listOf(0, 0, 1, -1)
    val dy = listOf(1, -1, 0, 0)
    fun solve() {
        val (r, c) = readln().split(" ").map { it.toInt() }
        this.r = r
        this.c = c
        val visited = MutableList<BooleanArray>(r) { BooleanArray(c) { false } }
        board = mutableListOf()
        for (i in 0 ..< r) {
            val line = readln().toMutableList()
            line.forEachIndexed { idx, it ->
                if (it == 'J') {
                    initPos = Pair(i, idx)
                    q.add(initPos)

                } else if (it == 'F') {
                    firePos = Pair(i, idx)
                    fq.add(firePos)
                }
            }

            board.add(line)
        }

        visited[initPos.first][initPos.second] = true

        var round = 0
        var found = false

        while (q.isNotEmpty()) {
            val node = q.poll()
            val x = node.first
            val y = node.second

            if (x == 0 || y == 0 || x == r - 1 || y == c - 1) {
                found = true
                break
            }

            fireBfs()

            repeat(4) {
                val nx = x + dx[it]
                val ny = y + dy[it]

                if (nx < 0 || ny < 0 || nx >= r || ny >= c) return@repeat

                if (board[nx][ny] == '.' && !visited[nx][ny]) {
                    nq.add(Pair(nx, ny))
                    visited[nx][ny] = true
                }
            }

            if (q.isEmpty()) {
                while (nq.isNotEmpty()) q.add(nq.poll())
                while (nfq.isNotEmpty()) fq.add(nfq.poll())
                round += 1
            }
        }

        if (!found) {
            println("IMPOSSIBLE")
        } else {
            println(round + 1)
        }
    }

    fun fireBfs() {
        while (fq.isNotEmpty()) {
            val node = fq.poll()
            val x = node.first
            val y = node.second

            repeat(4) {
                val nx = x + dx[it]
                val ny = y + dy[it]

                if (nx < 0 || ny < 0 || nx >= r || ny >= c) return@repeat

                if (board[nx][ny] == '.' || board[nx][ny] == 'J') {
                    nfq.add(Pair(nx, ny))
                    board[nx][ny] = 'F'
                }
            }
        }
    }
}
