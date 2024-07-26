package implementation.s

fun main() {
    val solution = Solution2067()
    solution.solve()
}


class Solution2067 {
    fun solve() {
        val cnt = readln().toInt()

        if (cnt == 0 || cnt == 1) {
            println(0)
            return
        }

        val baseWord = readln()
        var baseList = IntArray(26) {0}

        baseWord.forEach {
            baseList[it.code - 'A'.code] += 1
        }

        var res = 0

        repeat(cnt - 1) {
            val copyMap = baseList.copyOf()
            val word = readln()

            word.forEach {
                copyMap[it.code - 'A'.code] -= 1
            }
            if(copyMap.max() >= 2 || copyMap.min() <= -2 || copyMap.filter { it != 0}.size > 2) return@repeat
            if (copyMap.sum() in -1..1) res += 1
        }

        println(res)
    }
}