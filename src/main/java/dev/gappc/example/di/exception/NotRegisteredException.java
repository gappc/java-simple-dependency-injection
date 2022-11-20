package dev.gappc.example.di.exception;

/**
 * This exception is thrown if the dependency provider should create an instance
 * that was not registered.
 */
public class NotRegisteredException extends RuntimeException {

    public NotRegisteredException(String s) {
        super (s);
    }

}
