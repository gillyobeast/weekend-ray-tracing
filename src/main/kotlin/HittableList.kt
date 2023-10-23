class HittableList(hittable: Hittable, vararg hittables: Hittable) : Hittable {
    private val hittables = setOf(hittable).union(hittables.toSet())
    override fun hit(ray: Ray, tMin: Double, tMax: Double): HitRecord? =

        hittables.fold(null as HitRecord?) { closest, hittable ->
            // For each hittable in the list, if it hits between tMin and
            //  our closest hit so far (tMax if no hits so far), then
            //  continue with that hit as our closest hit.
            hittable.hit(ray, tMin, closest?.t ?: tMax) ?: closest
        }
}
