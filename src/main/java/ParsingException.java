public class ParsingException extends Exception {
    ParsingException(final Throwable cause) {
        super("Failure parsing input", cause);
    }
}
