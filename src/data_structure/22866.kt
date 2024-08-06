package data_structure

import java.util.Stack

fun main() {
    Solution22866().solve()
}

class Solution22866 {
    fun solve() {
        val n = readln().toInt()
        val list = readln().split(" ").map { it.toInt() }

        val lStack = Stack<Pair<Int, Int>>()

        val lList = MutableList<Int>(list.size) { -1 }
        val rList = MutableList<Int>(list.size) { -1 }
        val curList = MutableList<Int>(list.size) { 0 }

        list.forEachIndexed { idx, it ->
            while (lStack.isNotEmpty() && lStack.peek().first <= it) {
                lStack.pop()
            }

            if (lStack.isNotEmpty() && lStack.peek().first > it) {
                lList[idx] = lStack.peek().second
                curList[idx] += lStack.size
            }
            lStack.push(Pair(it, idx))
        }
        lStack.clear()

        for (i in list.indices.reversed()) {
            while (lStack.isNotEmpty() && lStack.peek().first <= list[i]) {
                lStack.pop()
            }
            if (lStack.isNotEmpty() && lStack.peek().first > list[i]) {
                rList[i] = lStack.peek().second
                curList[i] += lStack.size
            }
            lStack.push(Pair(list[i], i))
        }

        for (i in lList.indices) {
            if (curList[i] == 0) {
                println(0)
                continue
            }

            var str = curList[i].toString()

            if (lList[i] != -1 && rList[i] != -1) {
                if (i - lList[i] > rList[i] - i) str += " ${rList[i] + 1}"
                else str += " ${lList[i] + 1}"

            } else if (lList[i] != -1) {
                str += " ${lList[i] + 1}"
            } else {
                str += " ${rList[i] + 1}"
            }

            println(str)
        }
    }
}