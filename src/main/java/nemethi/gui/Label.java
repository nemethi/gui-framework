package nemethi.gui;

import java.util.Objects;

/**
 * A label that is used to display a small amount of text.
 */
public class Label extends AbstractComponent {

    private String text = "";

    /**
     * Creates a new {@code Label} with no text.
     */
    public Label() {

    }

    /**
     * Creates a new {@code Label} with the specified text.
     *
     * @param text the text to be displayed
     * @throws NullPointerException if the text is {@code null}
     */
    public Label(String text) {
        this.text = Objects.requireNonNull(text, "text");
    }

    /**
     * Returns the HTML representation of this label.
     *
     * @return the HTML string representing this label
     */
    @Override
    public String render() {
        return "<label " + getStyleWithDisplay() + ">" + text + "</label>";
    }

    /**
     * Returns the text associated with this label.
     *
     * @return the text of the label
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the displayed text of this label.
     *
     * @param text the text to be displayed
     * @throws NullPointerException if the text is {@code null}
     */
    public void setText(String text) {
        this.text = Objects.requireNonNull(text, "text");
    }
}
