data class Colour(val r: Double, val g: Double, val b: Double) {
    constructor(r: Number, g: Number, b: Number) : this(r.d, g.d, b.d)

    override fun toString(): String {
        return "${r.scale()} ${g.scale()} ${b.scale()}    "
    }

    private fun Double.scale() = (255.999 * this).toInt()
    operator fun times(scale: Double): Colour = onEachComponent(Double::times, scale)
    operator fun div(scale: Number): Colour = this * (1 / scale.d)
    operator fun plus(other: Colour) = onEachComponent(Double::plus, other)
    private fun onEachComponent(op: Double.(Double) -> Double, o: Colour) = Colour(r.op(o.r), g.op(o.g), b.op(o.b))
    private fun onEachComponent(op: Double.(Double) -> Double, o: Double) = Colour(r.op(o), g.op(o), b.op(o))
    private fun onEachComponent(op: Double.() -> Double) = Colour(r.op(), g.op(), b.op())
    fun gammaCorrect() = onEachComponent(Math::sqrt)

    companion object {
        val RED = Colour(1, 0, 0)
        val GREEN = Colour(0, 1, 0)
        val BLUE = Colour(0, 0, 1)
        val BLACK = Colour(0, 0, 0)
        val WHITE = Colour(1, 1, 1)
    }

}
