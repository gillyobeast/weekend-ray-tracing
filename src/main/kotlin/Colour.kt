data class Colour(val r: Double, val g: Double, val b: Double) {
    constructor(r: Number, g: Number, b: Number) : this(r.d, g.d, b.d)

    override fun toString(): String {
        return "${r.scale()} ${g.scale()} ${b.scale()}    "
    }

    private fun Double.scale() = (255.999 * this).toInt()
    operator fun times(scale: Double): Colour = Colour(r * scale, g * scale, b * scale)
    operator fun plus(other: Colour) = spread(Double::plus, other)
    private fun spread(op: Double.(Double) -> Double, o: Colour) = Colour(r.op(o.r), g.op(o.g), b.op(o.b))

}
