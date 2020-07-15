package nemethi.gui;

import java.util.Objects;

public class Button extends AbstractComponent {

    private String text = "";

    public Button() {
    }

    public Button(String text) {
        this.text = Objects.requireNonNull(text, "text");
    }

    @Override
    public String render() {
        return renderOpeningTag() + text + "</button>";
    }

    private String renderOpeningTag() {
        return "<button type=\"button\" style=\"" + getDisplayCssProperty() + "\">";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Button button = (Button) o;
        return text.equals(button.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "Button{" +
                "text='" + text + '\'' +
                "hidden'" + isHidden() + '\'' +
                '}';
    }
}
