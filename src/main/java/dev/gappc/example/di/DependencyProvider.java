package dev.gappc.example.di;

import dev.gappc.example.di.exception.InstanceCreationException;
import dev.gappc.example.di.exception.NotRegisteredException;

/**
 * Interface for a dependency provider.
 */
public interface DependencyProvider {

    /**
     * Register the class <code>clazz</code> to be used by the dependency provider.
     *
     * @param clazz The class to register.
     * @param <T>   The type of the class.
     */
    <T> void registerClass(Class<T> clazz);

    /**
     * Get an instance of the given class.
     * <p>
     * Note that the class has to be registered using {@link #registerClass(Class)} before an instance
     * can be created. Failing to do so will result in a {@link NotRegisteredException} when an
     * invoking this method here.
     *
     * @param clazz The class to be created.
     * @param <T>   The type of the class.
     * @return An instance of type <code>T</code>.
     * @throws NotRegisteredException    if a class should be instantiated that is not registered.
     * @throws InstanceCreationException if there was an error during the instantiation of the class.
     */
    <T> T getInstance(Class<T> clazz);

}
