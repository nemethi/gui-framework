package nemethi.gui;

import java.util.Objects;

public class Label extends AbstractComponent {

    private String text = "";

    public Label() {

    }

    public Label(String text) {
        this.text = Objects.requireNonNull(text, "text");
    }

    @Override
    public String render() {
        return "<label " + getStyleWithDisplay() + ">" + text + "</label>";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = Objects.requireNonNull(text, "text");
    }
}
