package cage;

import animal.Bird;

/**
 * Extends Cage directly (a bird enclosure is not a MammalCage).
 * Can hold any bird species - e.g. Eagle.
 */
public class BirdCage extends Cage<Bird> {
    public BirdCage(String label, int maxCapacity) {
        super(label, maxCapacity);
    }
}
