package dev.gappc.example.di.service;

import java.time.LocalTime;

/**
 * Simple demo service that returns a time instances.
 */
public class TimeService {

    /**
     * Return current {@link LocalTime}.
     *
     * @return Current LocalTime.
     */
    public LocalTime getTime() {
        return LocalTime.now();
    }

}
