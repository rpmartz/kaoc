package com.ryanpmartz.aoc.common.parsing

fun ints(line: String): List<Int> {
    val regex = Regex("-?[0-9]+")
    val matches = regex.findAll(line)

    return matches.map { it.value.toInt() }.toList()
}
