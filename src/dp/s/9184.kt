fun main() {
    val solution = Solution9184()
    solution.solve()
}

class Solution9184 {
    var memo = Array(51) { Array(51) { IntArray(51) { -1 } } }

    fun w(a: Int, b: Int, c: Int): Int{
        if (a <= 0 || b <= 0 || c <= 0) return 1

        if (memo[a][b][c] != -1) return memo[a][b][c]

        if (a > 20 || b > 20 || c > 20) {
            memo[a][b][c] = w(20, 20, 20)
            return memo[a][b][c]
        }
        if (a < b && b < c) {
            memo[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c)
            return memo[a][b][c]
        }
        else {
            memo[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a-1,b-1,c-1)
            return memo[a][b][c]
        }
    }

    fun solve() {
        while(true) {
            val (a, b, c) = readln().split(" ").map { it.toInt() }
            if (a == -1 && b == -1 && c == -1) break
            println("w($a, $b, $c) = ${w(a,b,c)}")
        }
    }
}