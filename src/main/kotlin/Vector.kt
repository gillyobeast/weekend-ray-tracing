import kotlin.math.abs
import kotlin.math.sqrt
import kotlin.random.Random

data class Vector(val x: Double, val y: Double, val z: Double) {
    constructor() : this(0.0, 0.0, 0.0)
    constructor(x: Number, y: Number, z: Number) : this(x.d, y.d, z.d)

    operator fun unaryMinus() = Vector(-x, -y, -z)
    operator fun get(i: Int) = when (i) {
        0 -> x;1 -> y; 2 -> z; else -> throw uoe()
    }

    private fun uoe() = UnsupportedOperationException()

    val lengthSquared get() = this dot this
    val length get() = sqrt(lengthSquared)

    operator fun plus(other: Vector) = spread(Double::plus, other)
    operator fun minus(other: Vector) = spread(Double::minus, other)
    operator fun times(other: Vector) = spread(Double::times, other)
    operator fun times(scale: Double) = Vector(x * scale, y * scale, z * scale)
    operator fun div(scale: Double) = this * (1 / scale)

    infix fun dot(other: Vector): Double = (x * other.x) + (y * other.y) + (z * other.z)
    infix fun cross(other: Vector): Vector = Vector(
        this.y * other.z - this.z * other.y,
        this.z * other.x - this.x * other.z,
        this.x * other.y - this.y * other.x
    )


    private fun spread(op: Double.(Double) -> Double, o: Vector) = Vector(x.op(o.x), y.op(o.y), z.op(o.z))

    fun normalised() = this / this.length
    private val nearZero = 1e-8

    fun nearZero(): Boolean = abs(x) < nearZero && abs(y) < nearZero && abs(z) < nearZero

    override fun toString(): String = "[$x, $y, $z]"

    companion object {
        fun randomInUnitCube(): Vector = Vector(Random.nextDouble(), Random.nextDouble(), Random.nextDouble())
        fun randomInUnitSphere(): Vector = generateSequence { randomInUnitCube() }.first { it.length <= 1 }
        fun randomUnitVector(): Vector = randomInUnitSphere().normalised()
    }
}
