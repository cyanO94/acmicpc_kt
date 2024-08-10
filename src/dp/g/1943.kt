package dp.g


fun main() {
    Solution1943().solve()
}

class Solution1943 {
    data class Coin(val value: Int, val quantity: Int)
    fun solve() {
        repeat(3) { _ ->
            val n = readln().toInt()
            val coins = mutableListOf<Coin>()
            var totalSum = 0

            repeat(n) {
                val coin = readln().split(" ").map{ it.toInt() }
                coins.add(Coin(coin[0], coin[1]))
                totalSum += coin[0] * coin[1]
            }

            if (totalSum % 2 != 0) {
                println("0")
                return@repeat
            }

            val target = totalSum / 2
            val money = MutableList<Boolean>(totalSum + 1) { false }

            money[totalSum] = true
            for (coin in coins) {
                val value = coin.value
                val quantity = coin.quantity


                for (i in target .. totalSum) {
                    if (money[i]) {
                        for (j in quantity downTo 1) {
                            if (i - value * j < target) continue
                            money[i - value * j] = true
                        }
                    }
                }
            }

            //println(money.mapIndexed { idx, it -> if(it == true) idx else 0 }.filter{ it != 0})
            if (money[target]) println("1")
            else println("0")
        }
    }
}