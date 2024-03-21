package implementation.g

fun main() {
    val solution = Solution5430()
    solution.solve()
}

class Solution5430 {
    fun solve() {
        val testCase = readln().toInt()

        for (i in 0 until testCase) {
            ab()
        }
    }

    fun ab() {
        var operations = readln()
        val length = readln().toInt()
        var strArr = readln()
        strArr = strArr.replace("[", "")
        strArr = strArr.replace("]", "")
        var arr = strArr.split(",").toMutableList()
        if (strArr.length == 0) arr = mutableListOf()

        operations = operations.replace("RR", "")

        var isError = false
        var direction = true
        run loop@ {
            operations.forEach {
                when (it) {
                    'R' -> {
                        //arr = arr.reversed().toMutableList()
                        direction = !direction
                    }
                    'D' -> {
                        if (arr.isEmpty()) {
                            println("error")
                            isError = true
                            return@loop
                        }
                        else {
                            if (direction) arr.removeFirst()
                            else arr.removeLast()
                        }
                    }
                }
            }
        }

        if (!isError) {
            if (direction) println(arr.joinToString(",", "[", "]"))
            else println(arr.reversed().joinToString(",", "[", "]"))
        }
    }
}