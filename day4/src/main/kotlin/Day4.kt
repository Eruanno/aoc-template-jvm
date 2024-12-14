/*fun main() {
    println(part1())
    println(part2())
}*/

fun main() {
    println(part1() == 2549)
    println(part2() == 2003)
}

fun part1(): Any {
    val lines = input.split("\r\n")
    var acc = 0
    val matrix = toCharMatrix(lines)

    var row = 0
    while (row < matrix.size) {
        var column = 0
        while (column < matrix[0].size) {
            if (matrix[row][column] == 'X') {
                // Up.
                if (row + 3 < matrix.size && matrix[row + 1][column] == 'M' && matrix[row + 2][column] == 'A' && matrix[row + 3][column] == 'S') {
                    acc++
                }
                // Up and Right.
                if (row + 3 < matrix.size && column + 3 < matrix[0].size && matrix[row + 1][column + 1] == 'M' && matrix[row + 2][column + 2] == 'A' && matrix[row + 3][column + 3] == 'S') {
                    acc++
                }
                // Right.
                if (column + 3 < matrix[0].size && matrix[row][column + 1] == 'M' && matrix[row][column + 2] == 'A' && matrix[row][column + 3] == 'S') {
                    acc++
                }
                // Right and Down.
                if (row - 3 >= 0 && column + 3 < matrix[0].size && matrix[row - 1][column + 1] == 'M' && matrix[row - 2][column + 2] == 'A' && matrix[row - 3][column + 3] == 'S') {
                    acc++
                }
                // Down.
                if (row - 3 >= 0 && matrix[row - 1][column] == 'M' && matrix[row - 2][column] == 'A' && matrix[row - 3][column] == 'S') {
                    acc++
                }
                // Down and Left.
                if (row - 3 >= 0 && column - 3 >= 0 && matrix[row - 1][column - 1] == 'M' && matrix[row - 2][column - 2] == 'A' && matrix[row - 3][column - 3] == 'S') {
                    acc++
                }
                // Left.
                if (column - 3 >= 0 && matrix[row][column - 1] == 'M' && matrix[row][column - 2] == 'A' && matrix[row][column - 3] == 'S') {
                    acc++
                }
                // Left and Up.
                if (column - 3 >= 0 && row + 3 < matrix.size && matrix[row + 1][column - 1] == 'M' && matrix[row + 2][column - 2] == 'A' && matrix[row + 3][column - 3] == 'S') {
                    acc++
                }
            }
            column++
        }
        row++
    }
    return acc
}

fun part2(): Any {
    val lines = input.split("\r\n")
    var acc = 0
    val matrix = toCharMatrix(lines)

    var row = 1
    while (row < matrix.size - 1) {
        var column = 1
        while (column < matrix[0].size - 1) {
            if (matrix[row][column] == 'A') {
                if (((matrix[row - 1][column - 1] == 'M' && matrix[row + 1][column + 1] == 'S') || (matrix[row - 1][column - 1] == 'S' && matrix[row + 1][column + 1] == 'M'))
                    && ((matrix[row - 1][column + 1] == 'M' && matrix[row + 1][column - 1] == 'S') || (matrix[row - 1][column + 1] == 'S' && matrix[row + 1][column - 1] == 'M'))
                ) {
                    acc++
                }
            }
            column++
        }
        row++
    }
    return acc
}

private fun toCharMatrix(strings: List<String>): List<List<Char>> {
    val maxLength = strings.maxOf { it.length }
    return strings.map { it.padEnd(maxLength, ' ').toList() }
}

private val input: String by lazy { readInput() }

private fun readInput(): String {
    return object {}.javaClass.getResource("Day4.input").readText()
}
