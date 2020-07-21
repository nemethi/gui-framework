package nemethi.gui;

/**
 * Serves as a basic building block for creating components.
 */
public abstract class AbstractComponent implements Component {

    private boolean hidden = false;

    /**
     * Returns the 'hidden' state of this component.
     * Components are visible by default. (i.e. they are not hidden)
     * <p>
     * Hidden components should not be rendered.
     *
     * @return {@code true} if this component is hidden
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * Set the 'hidden' state of this component.
     *
     * @param hidden the new value of the visibility state
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * If this component is hidden, return a CSS rule that prevents
     * displaying this component. Returns an empty string otherwise.
     *
     * @return a CSS rule if this component is hidden, otherwise an empty string
     */
    protected String getDisplayCssProperty() {
        return hidden ? "display: none;" : "";
    }

    /**
     * If this component is hidden, return a CSS rule assigned
     * to an HTML {@code style} attribute that prevents displaying this component.
     * Returns an empty string otherwise.
     *
     * @return a CSS rule assigned to an HTML {@code style} attribute if this component
     * is hidden, otherwise an empty string
     */
    protected String getStyleWithDisplay() {
        return hidden ? "style=\"" + getDisplayCssProperty() + "\"" : "";
    }
}
