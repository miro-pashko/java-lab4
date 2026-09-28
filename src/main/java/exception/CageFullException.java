package exception;

/**
 * Thrown when an enclosure has no free space left for an animal being placed.
 */
public class CageFullException extends RuntimeException {
    public CageFullException(String message) {
        super(message);
    }
}
