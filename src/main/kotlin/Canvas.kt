import java.time.Instant
import kotlin.random.Random

data class Canvas(
    val width: Int, val height: Int,
    val aspectRatio: Double = width.d / height.d,
) {
    val columns: IntRange = 0..<width
    val rows: IntRange = 0..<height

    private val random = Random(Instant.now().nano)

    override fun toString(): String {
        return "$width $height"
    }

    operator fun get(column: Number, row: Number): Pair<Double, Double> =
        column.d / (width - 1) to row.d / (height - 1)

    fun randomPointIn(column: Int, row: Int) =
        this[column + random.nextDouble(), row + random.nextDouble()]


}

