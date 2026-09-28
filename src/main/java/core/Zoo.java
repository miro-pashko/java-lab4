package core;

import animal.Animal;
import cage.Cage;

import java.util.ArrayList;
import java.util.List;

/**
 * The zoo: holds every enclosure. Cage is generic (Cage<T extends
 * Animal>), so a raw "List<Cage>" would only compile with unchecked-type
 * warnings and would not let the list hold a mix of LionCage, UngulateCage
 * and BirdCage in a type-safe way. Using the wildcard
 * "List<Cage<? extends Animal>>" instead lets the list hold an enclosure
 * for ANY animal subtype, while still being fully type-checked - we only
 * ever read from these enclosures here (getOccupiedSpaces() doesn't
 * depend on T), so an "extends" (producer) wildcard is exactly right.
 */
public class Zoo {

    public List<Cage<? extends Animal>> cages = new ArrayList<>();

    /** Total number of animals currently held across every enclosure in the zoo. */
    public int getCountOfAnimals() {
        int total = 0;
        for (Cage<? extends Animal> cage : cages) {
            total += cage.getOccupiedSpaces();
        }
        return total;
    }

    /** Adds an enclosure (for any animal type) to the zoo. */
    public void addCage(Cage<? extends Animal> cage) {
        cages.add(cage);
    }
}
