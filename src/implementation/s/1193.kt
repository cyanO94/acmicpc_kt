package implementation.s

fun main() {
    val solution = Solution1193()
    solution.solve()
}

class Solution1193 {
    fun solve() {
        var num = readln().toInt()

        var cnt = 1
        while (num >= cnt) {
            num -= cnt
            cnt += 1
        }

        if (num == 0) {
            cnt -= 1
            num = cnt
        }

        if (cnt % 2 == 0) {
            println("${num}/${cnt - num+ 1}")
        }
        else {
            println("${cnt - num+ 1}/${num}")
        }
    }
}