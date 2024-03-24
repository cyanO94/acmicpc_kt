package dp.g

fun main() {
    val solution = Solution1520()
    solution.solve()
}


class Solution1520 {
    lateinit var board: Array<List<Int>>
    lateinit var dp: Array<MutableList<Int>>

    var n = 0
    var m = 0

    fun solve() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        this.n = n
        this.m = m

        board = Array(n) { readln().split(" ").map { it.toInt() } }
        dp = Array(n) { MutableList<Int>(m) { -1 } }

        dfs(0, 0)

        println(dp[0][0])
    }

    val dx = listOf(0, 0, 1, -1)
    val dy = listOf(1, -1, 0, 0)

    fun dfs(x: Int, y: Int): Int {
        if (x == n - 1 && y == m - 1) return 1
        if (dp[x][y] != -1) return dp[x][y]

        dp[x][y] = 0
        repeat(4) {
            val nx = x + dx[it]
            val ny = y + dy[it]

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) return@repeat
            if (board[nx][ny] < board[x][y]) dp[x][y] += dfs(nx, ny)
        }

        return dp[x][y]
    }

}