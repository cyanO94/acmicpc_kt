package implementation.s

import java.util.*

fun main() {
    val lineNum = readln().toInt()
    val inputList = mutableListOf<String>()

    for (i in 1..lineNum) {
        val line = readln()
        inputList.add(line)
    }

    val solution = Solution1316()
    solution.solve(inputList)
}

class Solution1316 {
    var cnt = 0

    fun solve(inputList: List<String>) {
        inputList.forEach { it ->
            val alphaSet = mutableSetOf<Char>()

            var savedAlpha : Char = it[0]
            var savedIndex = 0
            alphaSet.add(savedAlpha)
            run loop@{
                it.forEachIndexed { idx, item ->
                    if (idx == 0) return@forEachIndexed
                    if (savedAlpha != item) {
                        if (alphaSet.contains(item)) {
                            return@loop
                        }
                        alphaSet.add(item)
                        savedIndex = idx
                        savedAlpha = item
                    }
                }
                cnt += 1
            }
        }

        println("$cnt")
    }
}