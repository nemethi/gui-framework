package nemethi.gui;

public class DefaultDisplayEngine implements DisplayEngine {

    private static final String HTML_BEGIN = "<!DOCTYPE html><html><head></head><body>";
    private static final String HTML_END = "</body></html>";

    @Override
    public String display(Component component) {
        return HTML_BEGIN + component.render() + HTML_END;
    }

}
