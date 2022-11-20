package dev.gappc.example.di;

import dev.gappc.example.di.service.DateService;
import dev.gappc.example.di.service.DateTimeService;
import dev.gappc.example.di.service.TimeService;

/**
 * Main entry point to the demo project.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("----DependencyProviderImpl----");
        DependencyProvider dependencyProvider = new DependencyProviderImpl();
        dependencyProvider.registerClass(DateService.class);
        dependencyProvider.registerClass(TimeService.class);
        dependencyProvider.registerClass(DateTimeService.class);

        DateService dateService = dependencyProvider.getInstance(DateService.class);
        System.out.println("Date: " + dateService.getDate());

        TimeService timeService = dependencyProvider.getInstance(TimeService.class);
        System.out.println("Time: " + timeService.getTime());

        DateTimeService dateTimeService = dependencyProvider.getInstance(DateTimeService.class);
        System.out.println("DateTime: " + dateTimeService.getDateTime());
    }

}
