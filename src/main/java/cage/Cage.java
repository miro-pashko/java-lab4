package cage;

import animal.Animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An enclosure that holds animals of type T (or any subtype of T).
 * T is bounded by Animal, and each subclass (directly, or through the
 * intermediate MammalCage class) fixes T to the species it is allowed to
 * hold - that is how the placement restriction ("only lions in the lion
 * enclosure", etc.) is enforced by the compiler rather than by a runtime
 * check:
 *   - BirdCage    extends Cage<Bird>            -> any bird species accepted
 *   - LionCage    extends MammalCage<Lion>       -> only Lion accepted
 *   - UngulateCage extends MammalCage<Ungulate>  -> Zebra or Giraffe accepted
 */
public abstract class Cage<T extends Animal> {

    private final String label;
    private final int maxCapacity;
    private final List<T> animals = new ArrayList<>();

    protected Cage(String label, int maxCapacity) {
        this.label = label;
        this.maxCapacity = maxCapacity;
    }

    public String getLabel() {
        return label;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getOccupiedSpaces() {
        return animals.size();
    }

    /** Read-only view of which animals currently occupy this enclosure. */
    public List<T> getAnimals() {
        return Collections.unmodifiableList(animals);
    }



    @Override
    public String toString() {
        return String.format("%s[%s, %d/%d occupied]",
                getClass().getSimpleName(), label, getOccupiedSpaces(), maxCapacity);
    }
}
