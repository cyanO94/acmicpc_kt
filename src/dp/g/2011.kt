package dp.g

fun main() {
    val solution = Solution2011()
    solution.solve()
}

class Solution2011 {
    lateinit var memo: LongArray
    val MOD = 1000000
    fun solve() {
        val line = readln()
        val numList = line.toList().map { it.digitToInt() }.toMutableList()
        if (numList[0] == 0) {
            println(0)
            return
        }

        numList.add(0, 0)

        memo = LongArray(line.length + 1) { 0 }
        memo[0] = 1
        memo[1] = 1

        run loop2@ {
            numList.forEachIndexed { idx, item ->
                if (idx == 0 || idx == 1) return@forEachIndexed

                if (item == 0) {
                    if (numList[idx - 1] > 2 || numList[idx - 1] == 0) {
                        println(0)
                        return@loop2
                    }
                    memo[idx] = memo[idx - 2]
                }
                else if (isAlpha(numList[idx - 1], item)) memo[idx] = (memo[idx - 2] + memo[idx - 1]) % MOD
                else memo[idx] = memo[idx - 1]
            }
            println(memo.last())
        }

    }
    fun isAlpha(c1: Int, c2: Int): Boolean {
        if (c1 == 0) return false
        return ((c1 * 10 + c2) <= 26)
    }
}