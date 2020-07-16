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
        return "<button type=\"button\" " + getStyleWithDisplay() + ">";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = Objects.requireNonNull(text, "text");
    }
}
