package brainstormers.ibm.happinesdashbord.exception;

public class PollNotFoundException extends  RuntimeException{
    public PollNotFoundException(String message) {
        super(message);
    }
}
