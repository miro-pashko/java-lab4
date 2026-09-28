package cage;

import animal.Mammal;

/**
 * The intermediate class between Cage and LionCage/UngulateCage in the
 * provided hierarchy (Вольєр -> Вольєр для ссавців -> Вольєр для левів /
 * Вольєр для копитних). Stays generic so each concrete subclass can still
 * fix its own allowed species.
 */
public abstract class MammalCage<T extends Mammal> extends Cage<T> {
    protected MammalCage(String label, int maxCapacity) {
        super(label, maxCapacity);
    }
}
