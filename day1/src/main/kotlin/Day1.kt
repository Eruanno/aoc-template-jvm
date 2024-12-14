import kotlin.math.abs

/*fun main() {
    println(part1())
    println(part2())
}*/

fun main() {
    println(part1() == 2164381)
    println(part2() == 20719933)
}

fun part1(): Any {
    val (locationsIds1, locationsIds2) = input.split('\n')
        .map { it.split("\\s+".toRegex()) }
        .map { it[0].toInt() to it[1].toInt() }
        .unzip()

    return locationsIds1.sorted().zip(locationsIds2.sorted()).sumOf { abs(it.first - it.second) }
}

fun part2(): Any {
    val (locationsIds1, locationsIds2) = input.split('\n')
        .map { it.split("\\s+".toRegex()) }
        .map { it[0].toInt() to it[1].toInt() }
        .unzip()

    val locationsOccurrences = locationsIds2.groupingBy { it }.eachCount()

    return locationsIds1.sumOf { it * locationsOccurrences.getOrDefault(it, 0) }
}

private val input: String by lazy { readInput() }

private fun readInput(): String {
    return object {}.javaClass.getResource("Day1.input").readText()
}
