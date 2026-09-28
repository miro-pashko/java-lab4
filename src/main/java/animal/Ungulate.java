package animal;

/**
 * Abstract category for hoofed mammals (Zebra, Giraffe) - the
 * intermediate class between Mammal and its two concrete species, per the
 * hierarchy diagram.
 */
public abstract class Ungulate extends Mammal {
    protected Ungulate(String name) {
        super(name);
    }
}
