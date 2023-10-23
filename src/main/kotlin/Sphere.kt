import kotlin.math.sqrt

data class Sphere(val center: Point, val radius: Double) : Hittable {
    override fun hit(ray: Ray, tMin: Double, tMax: Double): HitRecord? {
        val oc = ray.origin - center
        val a = ray.direction.lengthSquared
        val halfB = oc dot ray.direction
        val c = oc.lengthSquared - radius * radius
        val discriminant = (halfB * halfB) - (a * c)

        if (discriminant < 0) return null
        val sqrtD = sqrt(discriminant)

        var root = (-halfB - sqrtD) / a
        if (root !in tMin..tMax) root = (-halfB + sqrtD) / a
        if (root !in tMin..tMax) return null

        val hitPoint = ray[root]
        return HitRecord(hitPoint, (hitPoint - center) / radius, root)
    }
}
