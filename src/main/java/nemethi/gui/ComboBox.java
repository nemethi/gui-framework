package nemethi.gui;

import java.util.ArrayList;
import java.util.List;

/**
 * A drop-down list that may contain selectable items.
 */
public class ComboBox extends AbstractComponent {

    private List<String> items = new ArrayList<>();

    /**
     * Creates a new {@code ComboBox} without any items.
     */
    public ComboBox() {

    }

    /**
     * Creates a new {@code ComboBox} with the specified items.
     *
     * @param items the list of items
     * @throws NullPointerException if the list is {@code null}
     */
    public ComboBox(List<String> items) {
        this.items = new ArrayList<>(items);
    }

    /**
     * Returns the HTML representation of this combo box with all of its items.
     *
     * @return the HTML string representing this combo box and its items
     */
    @Override
    public String render() {
        return "<select " + getStyleWithDisplay() + ">" + renderItems() + "</select>";
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

    /**
     * Returns a copy of the items stored in this combo box.
     *
     * @return a copy of the items of this combo box
     */
    public List<String> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Sets the items of this combo box.
     *
     * @param items the list of items
     * @throws NullPointerException if the list is {@code null}
     */
    public void setItems(List<String> items) {
        this.items = new ArrayList<>(items);
    }
}
