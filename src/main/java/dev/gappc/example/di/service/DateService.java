package dev.gappc.example.di.service;

import java.time.LocalDate;

/**
 * Simple demo service that returns a date instances.
 */
public class DateService {

    /**
     * Return current {@link LocalDate}.
     *
     * @return Current LocalDate.
     */
    public LocalDate getDate() {
        return LocalDate.now();
    }

}
