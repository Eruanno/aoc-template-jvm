import java.math.BigInteger

/*fun main() {
    println(part1())
    println(part2())
}*/

fun main() {
    println(part1() == 170807108)
    println(part2() == 74838033)
}

fun part1(): Any {
    val regex = Regex("mul\\(\\d{1,3},\\d{1,3}\\)")
    return regex.findAll(input).map { sum(it.groupValues[0]) }.reduce { acc, num -> acc + num }
}

fun part2(): Any {
    val regex = Regex("mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)||")
    val commands = regex.findAll(input).map { it.groupValues[0] }.toList()
    var isEnabled = true
    var acc = BigInteger.ZERO
    for (i in commands) {
        if (i.isBlank())
            continue
        else if (i == "do()")
            isEnabled = true
        else if (i == "don't()")
            isEnabled = false
        else if (isEnabled)
            acc += sum(i)
    }
    return acc
}

private fun sum(input: String): BigInteger {
    val regex = Regex("\\d{1,3}")
    return multiply(regex.findAll(input).map { it.groupValues[0].toLong() }.toList())
}

private fun multiply(input: List<Long>): BigInteger {
    return BigInteger.valueOf(input[0]) * (BigInteger.valueOf(input[1]))
}

private val input: String by lazy { readInput() }

private fun readInput(): String {
    return object {}.javaClass.getResource("Day3.input").readText()
}
