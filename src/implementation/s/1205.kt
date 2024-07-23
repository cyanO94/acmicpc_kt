package implementation.s


fun main() {
    val solution = Solution1205()
    solution.solve()
}

class Solution1205 {
    fun solve() {
        val (n, s, p) = readln().split(" ").map { it.toInt() }
        if (n == 0) {
            println(1)
            return
        }

        val scoreList = readln().split(" ").map { it.toInt() }

        var cnt = 0
        var sameCnt = 0

        for (i in scoreList.indices) {
            if (scoreList[i] < s) {
                break
            } else if (scoreList[i] == s) {
                sameCnt += 1
            }
            cnt += 1

        }
        if (cnt >= p) {
            println(-1)
            return
        }

        println(cnt - sameCnt + 1)
    }
}


