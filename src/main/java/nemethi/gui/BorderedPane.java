package nemethi.gui;

/**
 * A simple component container with borders.
 */
public class BorderedPane extends Container {
    /**
     * {@inheritDoc}
     */
    @Override
    protected String renderOwnOpeningTag() {
        return "<div style=\"border: 1px solid black;" + getDisplayCssProperty() + "\">";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String renderOwnClosingTag() {
        return "</div>";
    }
}
