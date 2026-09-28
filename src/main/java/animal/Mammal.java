package animal;

/**
 * Abstract category for mammal species (Lion, Ungulate -> Zebra/Giraffe).
 */
public abstract class Mammal extends Animal {
    protected Mammal(String name) {
        super(name);
    }
}
