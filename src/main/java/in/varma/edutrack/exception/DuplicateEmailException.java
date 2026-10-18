package in.varma.edutrack.exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String emailAlreadyExists) {
        super(emailAlreadyExists);
    }
}
