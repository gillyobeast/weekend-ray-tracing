data class Colour(val r: Double, val g: Double, val b: Double) {
    override fun toString(): String {
        return "${r.scale()} ${g.scale()} ${b.scale()}    "
    }
    private fun Double.scale() = (255.999 * this).toInt()
}
