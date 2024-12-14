import kotlin.math.abs

/*fun main() {
    println(part1())
    println(part2())
}*/

fun main() {
    println(part1() == 299)
    println(part2() == 364)
}

fun part1(): Any {
    return input.split("\r\n")
        .map { it.split("\\s+".toRegex()).map(String::toInt) }
        .count(::isReportSafe)
}

fun isReportSafe(levels: List<Int>): Boolean {
    val direction = levels[0] > levels[1]
    return levels.zipWithNext().all { (a, b) ->
        direction == (a > b) && abs(b - a) in 1..3
    }
}

fun part2(): Any {
    return input.split("\r\n")
        .map { it.split("\\s+".toRegex()).map(String::toInt) }
        .count(::isReportSafeWithTolerance)
}

private fun isReportSafeWithTolerance(levels: List<Int>): Boolean {
    return isReportSafe(levels) || levels.indices
        .map { index -> levels.filterIndexed { i, _ -> i != index } }
        .any { isReportSafe(it) }
}

private val input: String by lazy { readInput() }

private fun readInput(): String {
    return object {}.javaClass.getResource("Day2.input").readText()
}
