package dev.gappc.example.di;

import dev.gappc.example.di.exception.InstanceCreationException;
import dev.gappc.example.di.exception.NotRegisteredException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Simple implementation of a dependency provider that can be used to
 * resolve and retrieve class instances for dependency injection.
 */
public final class DependencyProviderImpl implements DependencyProvider {

    private static final Set<Class<?>> KNOWN_CLASSES = new HashSet<>();

    @Override
    public <T> void registerClass(Class<T> clazz) {
        DependencyProviderImpl.KNOWN_CLASSES.add(clazz);
    }

    @Override
    public <T> T getInstance(Class<T> clazz) {
        // Handle registered classes only
        if (!DependencyProviderImpl.KNOWN_CLASSES.contains(clazz)) {
            throw new NotRegisteredException("Unknown class: " + clazz.getCanonicalName());
        }

        // Get all constructors of given class
        Constructor<?>[] constructors = clazz.getConstructors();

        // Iterate through all constructors and check if anyone can be instantiated
        for (Constructor<?> constructor : constructors) {
            // Get constructor parameters
            Class<?>[] parameterTypes = constructor.getParameterTypes();

            // Check if all constructor parameter classes are known (= registered with the dependency provider)
            boolean allConstructorParameterTypesKnown = Stream.of(parameterTypes).allMatch(DependencyProviderImpl.KNOWN_CLASSES::contains);

            // If all constructor parameter classes are known, try to create a new instance and return it on success
            if (allConstructorParameterTypesKnown) {
                return createInstance(constructor);
            }
        }

        throw new InstanceCreationException("Now suitable constructor found to instantiate class " + clazz.getCanonicalName());
    }

    // Suppress warning about unchecked cast for constructor invocation
    @SuppressWarnings("unchecked")
    private <T> T createInstance(Constructor<?> constructor) {
        // Create instances of all constructor parameters
        Object[] objects = Stream.of(constructor.getParameterTypes()).map(this::getInstance).toArray(Object[]::new);

        try {
            // Try to invoke the constructor and return instance
            return (T) constructor.newInstance(objects);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new InstanceCreationException("Could not create new instance of class " + constructor.getDeclaringClass().getCanonicalName(), e);
        }
    }

}
