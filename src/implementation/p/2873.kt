package implementation.p

fun main() {
    val solution = Solution2873()
    solution.solve()
}


class Solution2873 {
    val res = StringBuilder()
    lateinit var board : Array<List<Int>>
    var n = 0
    var m = 0

    fun solve() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        this.n = n
        this.m = m
        val board = Array(n) { readln().split(" ").map { it.toInt() } }
        var savedI = 0
        var savedJ = 0
        var savedValue = Integer.MAX_VALUE

        if (m % 2 == 1 || n % 2 == 1) {
            addAll()
        }
        else {
            for (i in 0 until n) {
                for (j in 0 until m) {
                    if ((i + j) % 2 == 0) continue
                    if (savedValue > board[i][j]) {
                        savedValue = board[i][j]
                        savedI = i
                        savedJ = j
                    }
                }
            }

            addAllMinusOne(savedI, savedJ)
        }

        println(res.toString())
    }

    fun addAll() {
        if (n % 2 == 1) {
            for (i in 0 until n) {
                if (i % 2 == 0) {
                    res.append("R".repeat(m - 1))
                }
                else {
                    res.append("L".repeat(m - 1))
                }

                if (i != n -1) res.append("D")
            }
        }
        else if(m % 2 == 1) {
            for (i in 0 until m) {
                if (i % 2 == 0) {
                    res.append("D".repeat(n - 1))
                }
                else {
                    res.append("U".repeat(n - 1))
                }

                if (i != m -1) res.append("R")
            }
        }
    }

    fun addAllMinusOne(x: Int, y: Int) {
        var isReverse = false

        for (i in 0 until n step 2) {
            if (x == i || x == i + 1) { // target
                for (j in 0 until m step 2) {
                    if (j == y) {
                        isReverse = true
                        res.append("RD")
                    }
                    else if (j + 1 == y) {
                        isReverse = true
                        res.append("DR")
                    }
                    else {
                        if (isReverse) res.append("URD")
                        else res.append("DRU")
                    }

                    if (j != m - 2) {
                        res.append("R")
                    }
                }
            }
            else { // noTarget
                if (!isReverse) res.append("R".repeat(m-1) + "D" + "L".repeat(m-1))
                else res.append("L".repeat(m-1) + "D" + "R".repeat(m-1))
            }

            if (i != n - 2) res.append("D")
        }
    }
}

// 메모리 초과 코드
/*
class Solution2873 {
    val dx = intArrayOf(0, 1, 0, -1)
    val dy = intArrayOf(1, 0, -1, 0)
    val dd = charArrayOf('R', 'D', 'L', 'U')
    var n = 0
    var m = 0
    lateinit var dp1: Array<MutableList<Int>>

    lateinit var visited : Array<MutableList<Int>>
    lateinit var board: Array<List<Int>>

    fun solve() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        this.n = n
        this.m = m
        board = Array(n) { readln().split(" ").map { it.toInt() } }

        dp1 = Array(n) { MutableList<Int>(m) { 0 } }
        visited = Array(n) { MutableList<Int>(m) { 0 } }

        visited[0][0] = 1

        dfs(0, 0, "", 0)

        println(savedDir)
    }

    var savedHappy = 0
    var savedDir = ""
    fun dfs(x: Int, y: Int, dir: String, value: Int): Int {
        if (x == n -1 && y == m - 1) {
            if (value > dp1[x][y]) {
                dp1[x][y] = value
                savedDir = dir
                savedHappy = value
            }

            return board[x][y]
        }

        if (dp1[x][y] != 0) {
            if (dp1[x][y] >= value) {
                return dp1[x][y]
            }
        }

        for (i in 0..3) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            val nd = dd[i]

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue
            if (visited[nx][ny] != 0) continue
            visited[nx][ny] = 1
            val dfsRes = dfs(nx, ny, dir + nd, value + board[nx][ny])
            if (dfsRes > dp1[x][y]) {
                dp1[x][y] = dfsRes
            }
            visited[nx][ny] = 0
        }

        return board[x][y]
    }
}*/
