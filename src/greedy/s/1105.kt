package greedy.s

fun main() {
    val solution = Solution1105()
    solution.solve()
}

class Solution1105 {
    fun solve() {
        var (l, r) = readln().split(" ").map { it.toMutableList() }
        var sameCnt = 0

        if (l.size != r.size) {
            println(0)
            return
        }


        while (l.isNotEmpty() && r.isNotEmpty()) {
            val ll = l.removeFirst()
            val rr = r.removeFirst()

            if (ll == '8' && rr == '8') {
                sameCnt += 1
            } else if (ll == rr) {
                continue
            } else {
                break
            }
        }

        println(sameCnt)
    }
}
