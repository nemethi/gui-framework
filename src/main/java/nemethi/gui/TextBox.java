package nemethi.gui;

import java.util.Objects;

/**
 * A text field that allows the user to enter text.
 * Displays a placeholder text if no text is typed in it.
 * An estimated size of displayed characters may be specified.
 */
public class TextBox extends AbstractComponent {

    private String placeholder = "";
    private int size = 20;

    /**
     * Creates a new {@code TextBox} without a placeholder text.
     * The size of the displayed characters is 20.
     */
    public TextBox() {
    }

    /**
     * Creates a new {@code TextBox} with the specified placeholder text displayed.
     * The size of the displayed characters is 20.
     *
     * @param placeholder the text to be displayed when the {@code TextBox} is empty
     * @throws NullPointerException if the placeholder is {@code null}
     */
    public TextBox(String placeholder) {
        this.placeholder = Objects.requireNonNull(placeholder, "placeholder");
    }

    /**
     * Returns the HTML representation of this text box.
     *
     * @return the HTML string representing this text box
     */
    @Override
    public String render() {
        return "<input " +
                "type=\"text\" " +
                renderPlaceholder() +
                "size=\"" + size + "\" " +
                getStyleWithDisplay() + "/>";
    }

    private String renderPlaceholder() {
        return placeholder.isEmpty() ? "" : "placeholder=\"" + placeholder + "\" ";
    }

    /**
     * Returns the placeholder text of this text box.
     *
     * @return the placeholder of this text box
     */
    public String getPlaceholder() {
        return placeholder;
    }

    /**
     * Sets the placeholder text of this text box.
     *
     * @param placeholder the placeholder text to be displayed
     * @throws NullPointerException if the placeholder is {@code null}
     */
    public void setPlaceholder(String placeholder) {
        this.placeholder = Objects.requireNonNull(placeholder, "placeholder");
    }

    /**
     * Returns the size of the displayed characters in this text box.
     *
     * @return the size of the displayed characters in this text box
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the size that roughly determines how many characters will be
     * displayed in this text box.
     *
     * @param size the size of the displayed characters
     * @throws IllegalArgumentException if the size is less than or equal to zero
     */
    public void setSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than zero");
        }
        this.size = size;
    }
}
