package cage;

import animal.Ungulate;

/**
 * A kind of MammalCage. Can hold any Ungulate - i.e. Zebra or Giraffe -
 * since both extend Ungulate. Because this class fixes the type parameter
 * to Ungulate, placeAnimal(Lion) will not even compile.
 */
public class UngulateCage extends MammalCage<Ungulate> {
    public UngulateCage(String label, int maxCapacity) {
        super(label, maxCapacity);
    }
}
