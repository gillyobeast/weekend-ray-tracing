import kotlin.math.tan

class Camera(verticalFovDegrees: Number, aspectRatio: Double) {

    private val origin: Point
    private val horizontal: Vector
    private val vertical: Vector
    private val lowerLeft: Point


    init {
        val theta = verticalFovDegrees.toRadians()
        val h = tan(theta / 2)

        val viewportHeight = 2.0 * h
        val viewportWidth = viewportHeight * aspectRatio
        val focalLength = 1.0

        origin = Point()
        horizontal = Vector(viewportWidth, 0, 0)
        vertical = Vector(0, viewportHeight, 0)
        lowerLeft = origin - .5 * horizontal - .5 * vertical - Vector(0, 0, focalLength)
    }


    operator fun get(u: Double, v: Double) =
        Ray(origin, lowerLeft + u * horizontal + v * vertical - origin)

}
