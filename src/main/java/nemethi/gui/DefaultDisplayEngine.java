package nemethi.gui;

/**
 * A {@code DisplayEngine} that displays a {@code Component} in an HTML page.
 */
public class DefaultDisplayEngine implements DisplayEngine {

    private static final String HTML_BEGIN = "<!DOCTYPE html><html><head></head><body>";
    private static final String HTML_END = "</body></html>";

    /**
     * Returns an HTML page containing the component as a string.
     *
     * @param component the component to display
     * @return the component embedded in an HTML page as a string
     */
    @Override
    public String display(Component component) {
        return HTML_BEGIN + component.render() + HTML_END;
    }

}
