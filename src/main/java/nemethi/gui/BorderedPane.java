package nemethi.gui;

public class BorderedPane extends Container {
    @Override
    protected String renderOwnOpeningTag() {
        return "<div style=\"border: 1px solid black;" + getDisplayCssProperty() + "\">";
    }

    @Override
    protected String renderOwnClosingTag() {
        return "</div>";
    }
}
