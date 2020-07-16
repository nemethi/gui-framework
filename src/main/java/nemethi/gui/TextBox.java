package nemethi.gui;

import java.util.Objects;

public class TextBox extends AbstractComponent {

    private String placeholder = "";
    private int size = 20;

    public TextBox() {
    }

    public TextBox(String placeholder) {
        this.placeholder = Objects.requireNonNull(placeholder, "placeholder");
    }

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

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = Objects.requireNonNull(placeholder, "placeholder");
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than zero");
        }
        this.size = size;
    }
}
