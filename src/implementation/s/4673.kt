package implementation.s

fun main() {
    val solution = Solution4673()
    solution.solve()
}

class Solution4673 {
    val MAXSIZE = 10000
    val board = MutableList(MAXSIZE + 1) { 0 }

    fun checkSelfNum(i : Int) {
        var res = 0
        var tmpI = i

        res += tmpI
        while (tmpI > 0) {
            res += tmpI % 10
            tmpI /= 10
        }

        if (res > MAXSIZE) return
        board[res] = res
    }

    fun solve() {
        for (i in 1 .. MAXSIZE) {
            checkSelfNum(i)
        }

        board[0] = 1

        val resList = board.mapIndexedNotNull { idx, it -> if (it == 0) idx else null }

        resList.forEachIndexed { idx, item ->
            println("$item")
        }
    }
}