public class ParsingException extends Exception {
    ParsingException(Throwable cause) {
        super("Failure parsing input", cause);
    }
}
