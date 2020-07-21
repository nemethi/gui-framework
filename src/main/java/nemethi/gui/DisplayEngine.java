package nemethi.gui;

/**
 * An entity that is capable of displaying a {@code Component}.
 */
public interface DisplayEngine {
    /**
     * Returns a string that represents the specified component.
     *
     * @param component the component to display
     * @return a string representing the component
     */
    String display(Component component);
}
