package com.example.komiwojazer

import java.util.*

class Algorithm(start: Int, distance: Array<IntArray>) {
    private var N: Int = 0
    private var start: Int = 0
    private lateinit var distance: Array<IntArray>
    private var minCost = Double.POSITIVE_INFINITY.toInt()
    private var isSolved: Boolean = false
    private val tour: MutableList<Int> = ArrayList()

    constructor(distance: Array<IntArray>) : this(0, distance) {
        N = distance.size
        this.start = start
        this.distance = distance
    }

    fun getTour(): List<Int> {
        if (!isSolved) solve()
        return tour
    }

    val getCost: Int
        get() {
            if (!isSolved) solve()
            return minCost
        }

    fun solve() {
        if (isSolved) return
        val END_STATE = (1 shl N) - 1
        val memo = Array(N) {
            arrayOfNulls<Int>(
                1 shl N
            )
        }

        for (end in 0 until N) {
            if (end == start) continue
            memo[end][1 shl start or (1 shl end)] = distance[start][end]
        }
        for (r in 3..N) {
            for (subset in combinations(r, N)) {
                if (notIn(start, subset)) continue
                for (next in 0 until N) {
                    if (next == start || notIn(next, subset)) continue
                    val subsetWithoutNext = subset xor (1 shl next)
                    var minDist = Double.POSITIVE_INFINITY.toInt()
                    for (end in 0 until N) {
                        if (end == start || end == next || notIn(end, subset)) continue
                        val newDistance = memo[end][subsetWithoutNext]!! + distance[end][next]
                        if (newDistance < minDist) {
                            minDist = newDistance
                        }
                    }
                    memo[next][subset] = minDist
                }
            }
        }

        for (i in 0 until N) {
            if (i == start) continue
            val tourCost = memo[i][END_STATE]!! + distance[i][start]
            if (tourCost < minCost) {
                minCost = tourCost
            }
        }
        var lastIndex = start
        var state = END_STATE
        tour.add(start)

        for (i in 1 until N) {
            var index = -1
            for (j in 0 until N) {
                if (j == start || notIn(j, state)) continue
                if (index == -1) index = j
                val prevDist = memo[index][state]!! + distance[index][lastIndex]
                val newDist = memo[j][state]!! + distance[j][lastIndex]
                if (newDist < prevDist) {
                    index = j
                }
            }
            tour.add(index)
            state = state xor (1 shl index)
            lastIndex = index
        }
        tour.add(start)
        Collections.reverse(tour)
        isSolved = true
    }

    private fun notIn(elem: Int, subset: Int): Boolean {
        return 1 shl elem and subset == 0
    }

    fun combinations(r: Int, n: Int): List<Int> {
        val subsets: MutableList<Int> = ArrayList()
        combinations(0, 0, r, n, subsets)
        return subsets
    }

    private fun combinations(set: Int, at: Int, r: Int, n: Int, subsets: MutableList<Int>) {
        var set = set
        val elementsLeftToPick = n - at
        if (elementsLeftToPick < r) return
        if (r == 0) {
            subsets.add(set)
        } else {
            for (i in at until n) {
                set = set or (1 shl i)
                combinations(set, i + 1, r - 1, n, subsets)
                set = set and (1 shl i).inv()
            }
        }
    }

}