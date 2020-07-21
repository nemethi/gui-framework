package nemethi.gui;

import java.util.Objects;

/**
 * A simple clickable button with an optional text on it.
 */
public class Button extends AbstractComponent {

    private String text = "";

    /**
     * Creates a new {@code Button} with no text on it.
     */
    public Button() {
    }

    /**
     * Creates a new {@code Button} with the specified text on it.
     *
     * @param text the text to be displayed on the button
     * @throws NullPointerException if the text is {@code null}
     */
    public Button(String text) {
        this.text = Objects.requireNonNull(text, "text");
    }

    /**
     * Returns the HTML representation of this button.
     *
     * @return the HTML string representing this button
     */
    @Override
    public String render() {
        return renderOpeningTag() + text + "</button>";
    }

    private String renderOpeningTag() {
        return "<button type=\"button\" " + getStyleWithDisplay() + ">";
    }

    /**
     * Returns the text displayed on this button.
     *
     * @return the text of this button
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text that is displayed on this button.
     *
     * @param text the text to be displayed on this button
     * @throws NullPointerException if the text is {@code null}
     */
    public void setText(String text) {
        this.text = Objects.requireNonNull(text, "text");
    }
}
