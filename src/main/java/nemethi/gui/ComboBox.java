package nemethi.gui;

import java.util.ArrayList;
import java.util.List;

public class ComboBox extends AbstractComponent {

    private List<String> items = new ArrayList<>();

    public ComboBox() {

    }

    public ComboBox(List<String> items) {
        this.items = new ArrayList<>(items);
    }

    @Override
    public String render() {
        return "<select " + getStyleWithDisplay() + ">" +
                renderItems() +
                "</select>";
    }

    private String renderItems() {
        StringBuilder builder = new StringBuilder();
        for (String item : items) {
            builder.append("<option>")
                    .append(item)
                    .append("</option>");
        }
        return builder.toString();
    }

    public List<String> getItems() {
        return new ArrayList<>(items);
    }

    public void setItems(List<String> items) {
        this.items = new ArrayList<>(items);
    }
}
