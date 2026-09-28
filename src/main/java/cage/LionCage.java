package cage;

import animal.Lion;

/**
 * A kind of MammalCage. Can only hold Lion. Because this class fixes the
 * type parameter to Lion, placeAnimal(Zebra) or placeAnimal(Giraffe) will
 * not even compile - the restriction is enforced by the type system, not
 * by a runtime check.
 */
public class LionCage extends MammalCage<Lion> {
    public LionCage(String label, int maxCapacity) {
        super(label, maxCapacity);
    }
}
