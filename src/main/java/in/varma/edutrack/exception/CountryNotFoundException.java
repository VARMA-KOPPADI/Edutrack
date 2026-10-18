package in.varma.edutrack.exception;

public class CountryNotFoundException extends RuntimeException{

    public CountryNotFoundException(String msg){
        super(msg);
    }
}
