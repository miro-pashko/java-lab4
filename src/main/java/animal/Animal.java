package animal;

/**
 * Common base for every animal in the zoo. Abstract: there is no such
 * thing as a plain "Animal" in the enclosure - every animal is a specific
 * species (Lion, Zebra, Giraffe, Eagle, ...).
 */
public abstract class Animal {

    private final String name;

    protected Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + name + "}";
    }
}
