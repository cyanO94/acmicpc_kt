package implementation.s

import java.util.LinkedList

fun main() {
    val solution = Solution1406()
    solution.solve()
}

class Solution1406 {
    fun solve() {
        val str = readln().toMutableList()
        val cnt = readln().toInt()
        val linkedList = LinkedList<Char>()
        linkedList.addAll(str)
        val iterator = linkedList.listIterator(linkedList.size)

        repeat(cnt) {
            val line = readln().split(" ")
            when (line[0]) {
                "P" -> {
                    iterator.add(line[1][0])
                }
                "B" -> {
                    if (iterator.hasPrevious()) {
                        iterator.previous()
                        iterator.remove()
                    }
                }
                "L" -> {
                    if (iterator.hasPrevious()) {
                        iterator.previous()
                    }
                }
                "D" -> {
                    if (iterator.hasNext()) {
                        iterator.next()
                    }
                }
            }
        }
        println(linkedList.joinToString(""))
    }
}