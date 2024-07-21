package implementation.g

import java.util.*
import kotlin.math.max

fun main() {
    val solution = Solution12100()
    solution.solve()
}

class Solution12100 {
    var max = 0
    fun solve() {
        val cnt = readln().toInt()
        val board = mutableListOf<MutableList<Int>>()

        repeat(cnt) {
            board.add(readln().split(" ").map { it.toInt() }.toMutableList())
        }

        dfs(board, 1)

        println(max)
    }

    fun dfs(board: MutableList<MutableList<Int>>, depth: Int) {
        if (depth > 5) {
            max = max(board.flatMap { it.toList() }.max(), max)
            return
        }

        for (i in 0..3) {
            val curBoard = move(board.map { it.toMutableList() }.toMutableList(), i)
            dfs(curBoard, depth + 1)
        }
    }



    fun move(board: MutableList<MutableList<Int>>, dir: Int): MutableList<MutableList<Int>> {
        val lineList = LinkedList<Int>() as Queue<Int>

        if (dir == 0) {
            for (j in board.indices) {
                for (i in board.indices.reversed()) {
                    if (board[i][j] != 0) {
                        lineList.add(board[i][j])
                    }
                }

                for (i in board.indices.reversed()) {
                    lineList.poll()?.let {
                        if (it == lineList.peek()) {
                            board[i][j] = it * 2
                            lineList.poll()
                        } else {
                            board[i][j] = it
                        }
                    } ?: run {
                        board[i][j] = 0
                    }
                }
            }
        }
        else if (dir == 1) {
            for (j in board.indices) {
                for (i in board.indices) {
                    if (board[i][j] != 0) {
                        lineList.add(board[i][j])
                    }
                }
                for (i in board.indices) {
                    lineList.poll()?.let {
                        if (it == lineList.peek()) {
                            board[i][j] = it * 2
                            lineList.poll()
                        } else {
                            board[i][j] = it
                        }
                    } ?: run {
                        board[i][j] = 0
                    }
                }
            }
        }
        else if (dir == 2) {
            for (i in board.indices) {
                for (j in board.indices.reversed()) {
                    if (board[i][j] != 0) {
                        lineList.add(board[i][j])
                    }
                }
                for (j in board.indices.reversed()) {
                    lineList.poll()?.let {
                        if (it == lineList.peek()) {
                            board[i][j] = it * 2
                            lineList.poll()
                        } else {
                            board[i][j] = it
                        }
                    } ?: run {
                        board[i][j] = 0
                    }
                }
            }
        }
        else if (dir == 3) {
            for (i in board.indices) {
                for (j in board.indices) {
                    if (board[i][j] != 0) {
                        lineList.add(board[i][j])
                    }
                }

                for (j in board.indices) {
                    lineList.poll()?.let {
                        if (it == lineList.peek()) {
                            board[i][j] = it * 2
                            lineList.poll()
                        } else {
                            board[i][j] = it
                        }
                    } ?: run {
                        board[i][j] = 0
                    }
                }
            }
        }

        return board
    }

    fun printBoard(board: MutableList<MutableList<Int>>) {
        board.forEach {
            println(it.joinToString(" "))
        }
    }
}