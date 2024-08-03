package data_structure

import java.util.Stack

fun main() {
    Solution9935().solve()
}

class Solution9935 {
    fun solve() {
        val str = readln()
        val bomb = readln().toCharArray()
        val stack = Stack<Char>()

        var idx = 0
        while (idx < str.length) {
            stack.push(str[idx])
            if (stack.size >= bomb.size && stack.peek() == bomb.last()) {
                var found = true
                for (i in bomb.indices) {
                    if (stack[stack.size - bomb.size + i] != bomb[i]) {
                        found = false
                    }
                    if (!found) {
                        break
                    }
                }

                if (found) {
                    repeat(bomb.size) {
                        stack.pop()
                    }
                }
            }

            idx += 1
        }

        if (stack.size == 0) {
            println("FRULA")
        } else {
            println(stack.joinToString(""))
        }
    }
}