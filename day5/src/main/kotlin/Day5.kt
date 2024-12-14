import java.util.*

/*fun main() {
    println(part1())
    println(part2())
}*/

fun main() {
    println(part1() == 5948)
    println(part2() == 3062)
}

fun part1(): Any {
    val (rules, updates) = readFileData()
    return updates.sumOf { checkUpdate(rules, it) }
}

fun part2(): Any {
    val (rules, updates) = readFileData()
    return updates.filter { checkUpdate(rules, it) == 0 }.sumOf { reorderUpdate(rules, it) }
}

private fun checkUpdate(rules: Map<Int, List<Int>>, update: List<Int>): Int {
    var i = 0
    while (i < update.size - 1) {
        var j = i + 1
        while (j < update.size) {
            val values = rules.getOrDefault(update[i], mutableListOf())
            if (values.contains(update[j])) {
                return 0
            }
            j++
        }
        i++
    }
    return update[update.size / 2]
}

private fun reorderUpdate(rules: Map<Int, List<Int>>, update: List<Int>): Int {
    var i = 0
    while (i < update.size - 1) {
        var j = i + 1
        while (j < update.size) {
            val values = rules.getOrDefault(update[i], mutableListOf())
            if (values.contains(update[j])) {
                Collections.swap(update, i, j)
                i = -1
                j = update.size
            }
            j++
        }
        i++
    }
    return update[update.size / 2]
}

private fun readFileData(): Pair<Map<Int, List<Int>>, List<List<Int>>> {
    val lines = input.split("\r\n")

    val map = mutableMapOf<Int, MutableList<Int>>()
    val numbersList = mutableListOf<List<Int>>()
    var isReadingPairs = true

    for (line in lines) {
        if (line.isBlank()) {
            isReadingPairs = false
            continue
        }

        if (isReadingPairs) {
            val (key, value) = line.split("|")
            val keyInt = key.toInt()
            val valueInt = value.toInt()
            map.computeIfAbsent(valueInt) { mutableListOf() }.add(keyInt)
        } else {
            val numbers = line.split(",").map { it.toInt() }
            numbersList.add(numbers)
        }
    }

    return map.mapValues { it.value.toList() } to numbersList
}

private val input: String by lazy { readInput() }

private fun readInput(): String {
    return object {}.javaClass.getResource("Day5.input").readText()
}
