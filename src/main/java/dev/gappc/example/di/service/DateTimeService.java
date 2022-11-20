package dev.gappc.example.di.service;

import java.time.LocalDateTime;

/**
 * Simple demo service that returns a date-time instances.
 *
 * This service relies on two other services ({@link DateService} and {@link TimeService}).
 */
public class DateTimeService {

    private final DateService dateService;
    private final TimeService timeService;

    public DateTimeService(DateService dateService, TimeService timeService) {
        this.dateService = dateService;
        this.timeService = timeService;
    }

    /**
     * Return current {@link LocalDateTime}.
     *
     * @return Current LocalDateTime.
     */
    public LocalDateTime getDateTime() {
        return LocalDateTime.of(dateService.getDate(), timeService.getTime());
    }
}
