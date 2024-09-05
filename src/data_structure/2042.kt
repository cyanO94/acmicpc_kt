package data_structure

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.pow

fun main() {
    Sol().solve()
}

class Sol {
    val inputList: MutableList<Long> = mutableListOf()
    var segList: MutableList<Long> = mutableListOf()

    fun solve() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val (N, M, K) = br.readLine().split(" ").map { it.toInt() }

        repeat(N) {
            inputList += br.readLine().toLong()
        }

        val a = 2.toDouble().pow(ceil(log2(N.toDouble())) + 1 )

        val size = 2.toDouble().pow(
            ceil(log2(N.toDouble())) + 1)

        segList = MutableList<Long>(a.toInt()) { 0L }

        init(1, 0, N - 1)

        repeat(M + K) {
            val (a, b, c) = br.readLine().split(" ").map { it.toLong() }
            when (a) {
                1L -> {
                    update(1, 0, N - 1, (b - 1).toInt(), c)
                }
                else -> { // 2
                    val sum = getSum(1, b - 1, c - 1, 0, (N - 1).toLong())
                    println(sum)
                }
            }
        }
    }

    fun getSum(idx: Int, left: Long, right: Long, start: Long, end: Long): Long {
        if (left > end || right < start) return 0L
        if (left <= start && right >= end) return segList[idx]

        val lSum = getSum(idx * 2, left, right, start, (start + end) / 2)
        val rSum = getSum(idx * 2 + 1, left, right, (start + end) / 2 + 1, end)

        return lSum + rSum
    }

    fun update(idx: Int, start: Int, end: Int, targetIdx: Int, newValue: Long) {
        if (targetIdx < start || targetIdx > end) return
        if (start == end) {
            segList[idx] = newValue
            inputList[targetIdx] = newValue
            return
        }

        update(idx * 2, start, (start + end) / 2, targetIdx, newValue)
        update(idx * 2 + 1, (start + end) / 2 + 1, end, targetIdx, newValue)
        segList[idx] = segList[idx * 2] + segList[idx * 2 + 1]
    }

    fun init(idx: Int, start: Int, end: Int) {
        if (start == end)
            segList[idx] = inputList[start]
        else {
            init(idx * 2, start, (start + end) / 2)
            init(idx * 2 + 1, (start +end) / 2 + 1, end)
            segList[idx] = segList[idx * 2] + segList[idx * 2 + 1]
        }
    }
}