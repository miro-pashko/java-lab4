import animal.Eagle;
import animal.Giraffe;
import animal.Lion;
import animal.Zebra;
import cage.BirdCage;
import cage.LionCage;
import cage.MammalCage;
import cage.UngulateCage;
import core.Zoo;
import exception.AnimalNotInCageException;
import exception.CageFullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CageTest {

    private LionCage lionCage;
    private UngulateCage ungulateCage;
    private BirdCage birdCage;

    @BeforeEach
    void setUp() {
        lionCage = new LionCage("Lion enclosure", 2);
        ungulateCage = new UngulateCage("Ungulate enclosure", 3);
        birdCage = new BirdCage("Bird enclosure", 2);
    }

    // -----------------------------------------------------------
    // Basic capacity accounting
    // -----------------------------------------------------------

    @Test
    void newCageHasNoOccupiedSpaces() {
        assertEquals(2, lionCage.getMaxCapacity());
        assertEquals(0, lionCage.getOccupiedSpaces());
    }

    // -----------------------------------------------------------
    // Placing animals: each enclosure accepts its allowed species
    // -----------------------------------------------------------

    @Test
    void lionCageCanHoldLions() {
        lionCage.placeAnimal(new Lion("Simba"));
        lionCage.placeAnimal(new Lion("Mufasa"));

        assertEquals(2, lionCage.getOccupiedSpaces());
    }

    @Test
    void ungulateCageCanHoldZebrasAndGiraffes() {
        ungulateCage.placeAnimal(new Zebra("Marty"));
        ungulateCage.placeAnimal(new Giraffe("Melman"));

        assertEquals(2, ungulateCage.getOccupiedSpaces());
    }

    @Test
    void birdCageCanHoldEagles() {
        birdCage.placeAnimal(new Eagle("Sam"));

        assertEquals(1, birdCage.getOccupiedSpaces());
    }

    // NOTE: There is no test here for "lionCage.placeAnimal(new Zebra(...))"
    // or "ungulateCage.placeAnimal(new Lion(...))" because those calls are
    // rejected at COMPILE TIME - LionCage extends MammalCage<Lion> and
    // UngulateCage extends MammalCage<Ungulate>, so the inherited
    // placeAnimal(...) method simply doesn't accept the other species.
    // That is the whole point of enforcing the restriction through
    // generics rather than through a runtime check.

    // -----------------------------------------------------------
    // Hierarchy shape: LionCage and
    // UngulateCage extend the intermediate MammalCage class.
    // -----------------------------------------------------------

    @Test
    void lionCageAndUngulateCageAreMammalCages() {
        assertInstanceOf(MammalCage.class, lionCage);
        assertInstanceOf(MammalCage.class, ungulateCage);
    }

    // -----------------------------------------------------------
    // Placing beyond capacity
    // -----------------------------------------------------------

    @Test
    void placingBeyondCapacityThrows() {
        lionCage.placeAnimal(new Lion("Simba"));
        lionCage.placeAnimal(new Lion("Mufasa"));

        CageFullException ex = assertThrows(CageFullException.class,
                () -> lionCage.placeAnimal(new Lion("Scar")));
        assertTrue(ex.getMessage().contains("Lion enclosure"));
        assertEquals(2, lionCage.getOccupiedSpaces());
    }

    // -----------------------------------------------------------
    // Removing animals
    // -----------------------------------------------------------

    @Test
    void removingAnimalFreesASpace() {
        Eagle sam = new Eagle("Sam");
        birdCage.placeAnimal(sam);
        assertEquals(1, birdCage.getOccupiedSpaces());

        birdCage.removeAnimal(sam);
        assertEquals(0, birdCage.getOccupiedSpaces());
        assertFalse(birdCage.getAnimals().contains(sam));
    }

    @Test
    void removingAnimalNotInCageThrows() {
        Eagle sam = new Eagle("Sam");
        // sam was never placed into birdCage

        AnimalNotInCageException ex = assertThrows(AnimalNotInCageException.class,
                () -> birdCage.removeAnimal(sam));
        assertTrue(ex.getMessage().contains("Bird enclosure"));
    }

    @Test
    void spaceFreedByRemovalCanBeReusedByPlacement() {
        Zebra marty = new Zebra("Marty");
        Giraffe melman = new Giraffe("Melman");
        Zebra alex = new Zebra("Alex");
        ungulateCage.placeAnimal(marty);
        ungulateCage.placeAnimal(melman);
        ungulateCage.placeAnimal(alex);

        ungulateCage.removeAnimal(marty);
        // now there is one free space again
        ungulateCage.placeAnimal(new Giraffe("Gloria"));

        assertEquals(3, ungulateCage.getOccupiedSpaces());
    }

    // -----------------------------------------------------------
    // Zoo: counting animals across a mix of enclosure types
    // -----------------------------------------------------------

    @Test
    void zooCountsAnimalsAcrossDifferentCageTypes() {
        Zoo zoo = new Zoo();

        lionCage.placeAnimal(new Lion("Simba"));
        lionCage.placeAnimal(new Lion("Mufasa"));
        zoo.addCage(lionCage); // 2 animals

        ungulateCage.placeAnimal(new Zebra("Marty"));
        zoo.addCage(ungulateCage); // 1 animal

        birdCage.placeAnimal(new Eagle("Sam"));
        birdCage.placeAnimal(new Eagle("Sarah"));
        zoo.addCage(birdCage); // 2 animals

        assertEquals(5, zoo.getCountOfAnimals());
    }

    @Test
    void zooWithNoCagesHasZeroAnimals() {
        Zoo zoo = new Zoo();
        assertEquals(0, zoo.getCountOfAnimals());
    }

    @Test
    void zooCountUpdatesAfterRemoval() {
        Zoo zoo = new Zoo();
        Zebra marty = new Zebra("Marty");
        ungulateCage.placeAnimal(marty);
        ungulateCage.placeAnimal(new Giraffe("Melman"));
        zoo.addCage(ungulateCage);

        assertEquals(2, zoo.getCountOfAnimals());

        ungulateCage.removeAnimal(marty);
        assertEquals(1, zoo.getCountOfAnimals());
    }
}
