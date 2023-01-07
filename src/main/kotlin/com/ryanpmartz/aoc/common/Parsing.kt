package com.ryanpmartz.aoc.common


fun parseInts(text: String): List<Int> {
    val regex = Regex("-?[0-9]+")

    val matchResults = regex.findAll(text)
    val results = mutableListOf<Int>()

    for (result in matchResults) {
        // use toIntExact to catch overflow/underflow
        results.add(Math.toIntExact(result.value.toLong()))
    }

    return results
}