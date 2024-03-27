package implementation.g

import kotlin.math.max

fun main() {
    val solution = Solution14500()
    solution.solve()
}

class Solution14500 {
    var maxValue = 0
    lateinit var arr: Array<List<Int>>
    lateinit var visited: Array<MutableList<Int>>

    var n = 0
    var m = 0

    fun solve() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        this.n = n
        this.m = m
        arr = Array(n) { readln().split(" ").map { it.toInt() } }
        visited = Array(n) { MutableList(m) {0} }

        for (i in 0 until n) {
            for (j in 0 until m) {
                visited[i][j] = 1
                dfs4(i, j, 0, 0)
                visited[i][j] = 0
                bfs(i, j)
            }
        }

        println(maxValue)

    }

    val dx = intArrayOf(0, 0, 1, -1)
    val dy = intArrayOf(1, -1, 0, 0)

    fun bfs(x: Int, y: Int) {
        var max4 = arr[x][y]
        val l4 = mutableListOf<Int>()

        for (i in 0..3) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue
            l4.add(arr[nx][ny])
        }

        if (l4.size < 3) return

        l4.sortDescending()
        max4 += l4[0] + l4[1] + l4[2]
        maxValue = max(max4, maxValue)
    }
    fun dfs4(x: Int, y: Int, depth: Int, value: Int) {
        // println("$x $y / $value // $depth")
        if (depth >= 3) {
            maxValue = max(value + arr[x][y], maxValue)
            return
        }

        for (i in 0..3) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue
            if (visited[nx][ny] == 0) {
                visited[nx][ny] = 1
                dfs4(nx, ny, depth + 1, value + arr[x][y])
                visited[nx][ny] = 0
            }
        }
    }
}