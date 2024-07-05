package dp.g

import kotlin.math.max

fun main() {
    val solution = Solution9251()
    solution.solve()
}

class Solution9251 {
    lateinit var memo: Array<IntArray>
    fun solve() {
        val word1 = readln()
        val word2 = readln()

        val longWord = if (word1.length >= word2.length) word1 else word2
        val shortWord = if (word1.length >= word2.length) word2 else word1
        memo = Array (longWord.length) {IntArray(longWord.length) }

        shortWord.forEachIndexed { idx, item ->
            longWord.forEachIndexed { iidx, iitem ->
                if (item == iitem) {
                    memo[idx][iidx] = max(getMemo(idx - 1, iidx - 1) + 1, getMemo(idx, iidx - 1))
                }
                else {
                    memo[idx][iidx] = max(getMemo(idx - 1, iidx), getMemo(idx, iidx - 1))
                }
            }
        }

        println(memo[shortWord.length - 1][longWord.length - 1])
    }

    fun getMemo(x: Int, y: Int): Int {
        if (x < 0|| y < 0) return 0
        return memo[x][y]
    }
}