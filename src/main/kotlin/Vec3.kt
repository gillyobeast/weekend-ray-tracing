import kotlin.math.sqrt

class Vec3(val x: Double, val y: Double, val z: Double) {
    // convenience constructor
    constructor(x: Int, y: Int, z: Int) : this(x.toDouble(), y.toDouble(), z.toDouble())

    val r = x;
    val g = y;
    val b = z;


    operator fun unaryPlus() = this
    operator fun unaryMinus() = Vec3(-x, -y, -z)
    operator fun get(int: Int): Double = when (int) {
        0 -> x; 1 -> y; 2 -> z; else -> throw NoSuchElementException()
    }

    operator fun plus(other: Vec3) = Vec3(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Vec3) = Vec3(x - other.x, y - other.y, z - other.z)
    operator fun times(other: Vec3) = Vec3(x * other.x, y * other.y, z * other.z)
    operator fun div(other: Vec3) = Vec3(x / other.x, y / other.y, z / other.z)
    operator fun times(scale: Double) = Vec3(x * scale, y * scale, z * scale)
    operator fun div(scale: Double) = Vec3(x / scale, y / scale, z / scale)

    infix fun dot(other: Vec3): Double = x * other.x + y + other.y + z * other.z
    infix fun cross(other: Vec3): Vec3 = Vec3(
        y * other.z - z * other.y,
        -(x * other.z - z * other.x),
        x * other.y - y * other.x
    )

    fun makeUnitVector(): Vec3 = this / length()

    fun squaredLength() = x * x + y * y + z * z
    fun length() = sqrt(squaredLength())

}
typealias Colour = Vec3
typealias Point = Vec3

operator fun Double.times(vec3: Vec3) = Vec3(this * vec3.x, this * vec3.y, this * vec3.z)