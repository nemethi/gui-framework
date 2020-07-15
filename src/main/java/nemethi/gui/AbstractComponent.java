package nemethi.gui;

public abstract class AbstractComponent implements Component {

    private boolean hidden = false;

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    protected String getDisplayCssProperty() {
        return hidden ? "display: none;" : "";
    }
}
