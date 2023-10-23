data class Canvas(
    val width: Int, val height: Int,
    val aspectRatio: Double = width.d / height.d,
) {
    val columns: IntRange = 0..<width
    val rows: IntRange = 0..<height
    override fun toString(): String {
        return "$width $height"
    }

    operator fun get(column: Int, row: Int): Pair<Double, Double> =
        column.d / (width - 1) to row.d / (height - 1)
}

