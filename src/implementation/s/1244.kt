package implementation.s

fun main() {
    val solution = Solution1244()
    solution.solve()
}

class Solution1244 {
    lateinit var switchList: MutableList<Boolean>
    fun solve() {
        val a = readln()
        switchList = readln().split(" ").map { it == "1" }.toMutableList()
        val studentCnt = readln().toInt()

        val persons = mutableListOf<List<Int>>()
        repeat(studentCnt) {
            persons.add(readln().split(" ").map { it.toInt() })
        }

        persons.forEach {
            when (it[0]) {
                1 -> {
                    doMan(it[1])
                }

                2 -> {
                    doWoman(it[1])
                }
            }
        }

        val printList = switchList.map { if (it) 1 else 0 }
        var cur = 0
        while (cur < switchList.size) {
            var start = cur
            var end = if (start + 20 < switchList.size) start + 20 else switchList.size

            println(printList.slice(start..<end).joinToString(" "))
            cur += 20
        }
    }

    fun doMan(num: Int) {
        var cx = num
        var mul = 2
        while (cx <= switchList.size) {
            switchList[cx - 1] = !switchList[cx - 1]

            cx = num * mul
            mul += 1
        }
    }

    fun doWoman(num: Int) {
        var mid = num - 1
        var cnt = 1

        switchList[mid] = !switchList[mid]

        while (mid + cnt < switchList.size && mid - cnt >= 0) {
            if (switchList[mid + cnt] == switchList[mid - cnt]) {
                switchList[mid + cnt] = !switchList[mid + cnt]
                switchList[mid - cnt] = !switchList[mid - cnt]
                cnt += 1
            } else {
                break
            }
        }
    }
}