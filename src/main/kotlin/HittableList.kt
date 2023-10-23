class HittableList(vararg hittables: Hittable) : Hittable {
    private val hittables = hittables.asList()
    override fun hit(ray: Ray, tMin: Double, tMax: Double): HitRecord? {
        return hittables.fold(null as HitRecord?) { closest, hittable ->
            // For each hittable in the list, if it hits between tMin and
            //  our closest hit so far (tMax if no hits so far), then
            //  continue with that hit as our closest hit.
            hittable.hit(ray, tMin, closest?.time ?: tMax) ?: closest
        }
    }
}