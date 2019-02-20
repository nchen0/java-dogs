package com.lambda.javadogs;

public class DogNotFoundException extends RuntimeException {
    public DogNotFoundException(Long id) {
        super ("Could not find Dog " + id);
    }
}
