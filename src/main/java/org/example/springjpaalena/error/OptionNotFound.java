package org.example.springjpaalena.error;

public class OptionNotFound extends RuntimeException {
    public OptionNotFound(String message) {
        super(message);
    }
}
