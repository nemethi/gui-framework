package nemethi.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Container extends AbstractComponent {

    private static final String TABLE_OPENING = "<table>";
    private static final String TABLE_CLOSING = "</table>";
    private static final String ROW_OPENING = "<tr>";
    private static final String ROW_CLOSING = "</tr>";
    private static final String DATA_OPENING = "<td>";
    private static final String DATA_CLOSING = "</td>";

    protected List<List<Component>> components = new ArrayList<>();
    private Component lastAdded = null;

    protected abstract String renderOwnOpeningTag();

    protected abstract String renderOwnClosingTag();

    public void addNextToLast(Component component) {
        if (components.isEmpty()) {
            addToNewRow(component);
        } else {
            addToLastComponentRow(component);
        }
    }

    public void addUnderLast(Component component) {
        addToNewRow(component);
    }

    @Override
    public final String render() {
        StringBuilder builder = new StringBuilder();
        builder.append(renderOwnOpeningTag()).append(TABLE_OPENING);
        for (List<Component> componentRow : components) {
            builder.append(ROW_OPENING)
                    .append(renderRow(componentRow))
                    .append(ROW_CLOSING);
        }
        builder.append(TABLE_CLOSING).append(renderOwnClosingTag());
        return builder.toString();
    }

    private String renderRow(List<Component> components) {
        StringBuilder builder = new StringBuilder();
        for (Component component : components) {
            builder.append(DATA_OPENING)
                    .append(component.render())
                    .append(DATA_CLOSING);
        }
        return builder.toString();
    }

    List<List<Component>> getComponents() {
        return Collections.unmodifiableList(components);
    }

    private void addToNewRow(Component component) {
        List<Component> row = new ArrayList<>();
        row.add(component);
        lastAdded = component;
        components.add(row);
    }

    private void addToLastComponentRow(Component component) {
        for (List<Component> componentList : components) {
            if (componentList.contains(lastAdded)) {
                componentList.add(component);
                lastAdded = component;
                break;
            }
        }
    }
}
