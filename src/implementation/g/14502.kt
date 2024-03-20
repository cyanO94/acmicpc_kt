package implementation.g

import java.util.*
import kotlin.math.max

lateinit var board: Array<IntArray>

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    board = Array(n) { readln().split(" ").map{it.toInt()}.toIntArray() }

    val solution = Solution14502()
    solution.solve(n, m)
}

class Solution14502 {
    var empty = mutableListOf<Pair<Int, Int>>()
    var wall = mutableListOf<Pair<Int, Int>>()
    var vir = mutableListOf<Pair<Int, Int>>()
    var n = 0
    var m = 0
    var cnt = 0

    fun solve(n:Int, m: Int) {
        this.n = n
        this.m = m

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (board[i][j] == 1) wall.add(Pair(i,j))
                else if (board[i][j] == 2) vir.add(Pair(i,j))
                else empty.add(Pair(i,j))
            }
        }
        pick(n, m)

        println("$cnt")
    }

    var wallCnt = 0

    fun pick(n:Int, m: Int) {
        if (wallCnt == 3) {
            cnt = max(cnt, checkCount())
        }
        else {
            for (i in 0 until n) {
                for (j in 0 until m) {
                    if (board[i][j] == 0) {
                        board[i][j] = 1
                        wall.add(Pair(i, j))
                        wallCnt += 1
                        pick(n, m)
                        wallCnt -= 1
                        wall.remove(Pair(i,j))
                        board[i][j] = 0
                    }
                }
            }
        }
    }

    fun checkCount() :Int {
        // bfs
        val q = ArrayDeque<Pair<Int, Int>>()
        val visited = mutableSetOf<Pair<Int, Int>>()
        val cpBoard = board.map {it.clone()}.toTypedArray()
        vir.forEach {
            q.add(it)
        }

        wall.forEach {
            cpBoard[it.first][it.second] = 1
        }

        val dx = intArrayOf(0, 0, 1, -1)
        val dy = intArrayOf(1, -1, 0, 0)

        while (!q.isEmpty()) {
            val node = q.poll()
            if (visited.contains(node)) continue
            visited.add(node)

            for (i in 0 until 4) {
                val nx = node.first + dx[i]
                val ny = node.second + dy[i]

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue

                if (cpBoard[nx][ny] == 0) {
                    q.add(Pair(nx, ny))
                    cpBoard[nx][ny] = 2
                }
            }
        }

        return cpBoard.flatMap { it.toList() }.count {it == 0}
    }
}