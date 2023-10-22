data class Canvas(val width: Int, val height: Int) {
    val columns: IntRange = 0..<height
    val rows: IntRange = 0..<height

    override fun toString(): String {
        return "$width $height"
    }
}

