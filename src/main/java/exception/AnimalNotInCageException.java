package exception;

/**
 * Thrown when trying to remove an animal that is not currently in the enclosure.
 */
public class AnimalNotInCageException extends RuntimeException {
    public AnimalNotInCageException(String message) {
        super(message);
    }
}
