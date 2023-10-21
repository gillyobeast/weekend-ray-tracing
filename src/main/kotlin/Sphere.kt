import kotlin.math.sqrt

class Sphere(val center: Point, val radius: Double) : Hittable {
    constructor(center: Point, radius: Int) : this(center, radius.toDouble())

    override fun hit(ray: Ray, tMin: Double, tMax: Double): HitRecord? {
        val oc = ray.origin - center
        val a = ray.direction dot ray.direction
        val b = oc dot ray.direction
        val c = (oc dot oc) - (radius * radius)
        val discriminant = (b * b) - (a * c)

        if (discriminant <= 0) return null

        val root1 = (-b - sqrt(discriminant)) / a
        if (root1 in tMin..tMax) return hitRecord(ray, root1)
        val root2 = (-b + sqrt(discriminant)) / a
        if (root2 in tMin..tMax) return hitRecord(ray, root2)

        return null
    }

    private fun hitRecord(ray: Ray, t: Double): HitRecord {
        val point = ray.pointAtParameter(t)
        return HitRecord(t, point, (point - center) / radius)
    }
}