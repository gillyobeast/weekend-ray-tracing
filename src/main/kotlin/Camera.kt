import kotlin.math.tan

class Camera(lookFrom: Point, lookAt: Point, up: Vector, verticalFovDegrees: Number, aspectRatio: Double) {

    private val origin: Point
    private val horizontal: Vector
    private val vertical: Vector
    private val lowerLeft: Point


    init {
        val theta = verticalFovDegrees.toRadians()
        val h = tan(theta / 2)

        val viewportHeight = 2.0 * h
        val viewportWidth = viewportHeight * aspectRatio

        val w = (lookFrom - lookAt).normalised()
        val u = (up cross w).normalised()
        val v = w cross u

        origin = Point()
        horizontal = Vector(viewportWidth, 0, 0) * u
        vertical = Vector(0, viewportHeight, 0) * v
        lowerLeft = origin - .5 * horizontal - .5 * vertical - w
    }


    operator fun get(s: Double, t: Double) =
        Ray(origin, lowerLeft + s * horizontal + t * vertical - origin)

}
