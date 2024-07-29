package implementation.g

fun main() {
    val solution = Solution7682()
    solution.solve()
}

class Solution7682 {
    var board = mutableListOf<Char>()
    fun solve() {
        while (true) {
            board = readln().toMutableList()
            if (board.joinToString("") == "end") {
                break
            }

            // 개수 차이
            if (board.count { it == 'X' } - board.count { it == 'O' } !in 0.. 1) {
                println("invalid")
                continue
            }

            // 완성 라인
            val xCnt = getLineCnt('X')
            val yCnt = getLineCnt('O')

            if (xCnt > 0 && yCnt > 0) {
                println("invalid")
                continue
            }

            if (xCnt == 0 && yCnt == 0) {
                if (board.contains('.')) {
                    println("invalid")
                } else {
                    println("valid")
                }
                continue
            }

            var found = false
            if (xCnt > 0) {
                for (i in 0..8) {
                    if (board[i] == 'X') {
                        board[i] = '.'
                        val xxCnt = getLineCnt('X')
                        board[i] = 'X'
                        if (xxCnt == 0) {
                            found = true
                        }
                    }

                    if (found) {
                        break
                    }
                }

                if (found && board.count { it == 'X' } - board.count { it == 'O' } != 1) {
                    found = false
                }
            }

            if (yCnt > 0) {
                for (i in 0..8) {
                    if (board[i] == 'O') {
                        board[i] = '.'
                        val xxCnt = getLineCnt('O')
                        board[i] = 'O'
                        if (xxCnt == 0) {
                            found = true
                        }
                    }

                    if (found) {
                        break
                    }
                }

                if (found && board.count { it == 'X' } != board.count { it == 'O' }) {
                    found = false
                }
            }

            if (found) {
                println("valid")
            } else {
                println("invalid")
            }

        }
    }

    fun getLineCnt(chr: Char): Int {
        var lineCnt = 0
        for (i in 0..2) {
            val base = chr
            if (base == '.') continue
            var found = true
            for (j in 0..2) {
                if (board[i*3 + j] != base) {
                    found = false
                }
            }

            if (found) {
                lineCnt += 1
            }
        }

        for (i in 0..2) {
            val base = chr
            var found = true
            if (base == '.') continue

            for (j in 0..2) {
                if (board[j*3 + i] != base) {
                    found = false
                }
            }

            if (found) {
                lineCnt += 1
            }
        }
        // 0 4 8
        if (board[0] == chr && (board[0] == board[4]) && (board[4] == board[8])) {
            lineCnt += 1
        }
        if (board[2] == chr && (board[2] == board[4]) && (board[4] == board[6])) {
            lineCnt += 1
        }

        return lineCnt
    }
}