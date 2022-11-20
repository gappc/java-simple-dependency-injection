package dev.gappc.example.di.exception;

/**
 * This exception is thrown if there was an error during the creation of a class instance.
 */
public class InstanceCreationException extends RuntimeException {

    public InstanceCreationException(String s) {
        super (s);
    }

    public InstanceCreationException(String s, ReflectiveOperationException e) {
        super(s, e);
    }

}
