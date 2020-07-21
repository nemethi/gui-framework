package nemethi.gui;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A component that contains {@code Tab}s which contain other components.
 * Enables the user to switch between the tabs.
 */
public class TabbedPane extends AbstractComponent {

    private static final String TABLINK_BUTTON_STYLE = "style=\"background-color: inherit; border: none; float: left; outline: none; padding: 14px 16px;\"";
    private static final String TABLINK_DIV_STYLE = "style=\"overflow: hidden; border: solid 1px black; background-color: #cccccc;\"";
    private static final String SHOW_TAB_JAVASCRIPT = "function showTab(e,t){var l,n,c;for(n=document.getElementsByClassName(\"tabcontent\"),l=0;l<n.length;l++)n[l].style.display=\"none\";for(c=document.getElementsByClassName(\"tablinks\"),l=0;l<c.length;l++)c[l].style.backgroundColor=\"#cccccc\";document.getElementById(t).style.display=\"block\",e.currentTarget.style.backgroundColor=\"#888888\"}document.getElementsByClassName(\"tablinks\")[0].click();";
    private static final String DIV_CLOSING = "</div>";

    private final List<Tab> tabs = new ArrayList<>();

    /**
     * Adds a new {@code Tab} to this pane.
     *
     * @param tab the tab to be added
     * @throws NullPointerException if the tab is {@code null}
     */
    public void addTab(Tab tab) {
        Tab newTab = new Tab(tab);
        newTab.setId("Tabcontent" + (tabs.size() + 1));
        tabs.add(newTab);
    }

    /**
     * Returns the HTML representation of this pane and all of its tabs.
     *
     * @return the HTML string representing this pane
     */
    @Override
    public String render() {
        StringBuilder builder = new StringBuilder();
        builder.append("<div ")
                .append(getStyleWithDisplay())
                .append(">")
                .append(renderTablinks());
        for (Tab tab : tabs) {
            builder.append(tab.render());
        }
        builder.append("<script>")
                .append(SHOW_TAB_JAVASCRIPT)
                .append("</script>")
                .append(DIV_CLOSING);
        return builder.toString();
    }

    private String renderTablinks() {
        StringBuilder builder = new StringBuilder();
        builder.append("<div " + TABLINK_DIV_STYLE + ">");
        for (Tab tab : tabs) {
            builder.append("<button class=\"tablinks\" ")
                    .append("onclick=\"showTab(event, '").append(tab.getId()).append("')\" ")
                    .append(TABLINK_BUTTON_STYLE).append(">")
                    .append(tab.getTitle())
                    .append("</button>");
        }
        builder.append(DIV_CLOSING);
        return builder.toString();
    }

    List<Tab> getTabs() {
        return new ArrayList<>(tabs);
    }

    /**
     * A container that if it is paired with a {@code TabbedPane} enables
     * the user to switch between displayed components.
     */
    public static class Tab extends Container {

        private String title;
        private String id = "";

        /**
         * Creates a new {@code Tab} with a title displayed on the tab.
         *
         * @param title the title to be displayed on the tab
         * @throws NullPointerException     if the title is null
         * @throws IllegalArgumentException if the title is empty
         */
        public Tab(String title) {
            this.title = Objects.requireNonNull(title, "title");
            if (title.isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty");
            }
        }

        Tab(Tab tab) {
            Objects.requireNonNull(tab, "tab");
            this.title = tab.title;
            this.id = tab.id;
            this.components = tab.components;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected String renderOwnOpeningTag() {
            return "<div class=\"tabcontent\" " +
                    "id=\"" + id + "\" " +
                    "style=\"display: none; padding: 6px 12px; border: 1px solid black; border-top: none;\">";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected String renderOwnClosingTag() {
            return DIV_CLOSING;
        }

        /**
         * Returns the title of this tab.
         *
         * @return the title displayed on this tab
         */
        public String getTitle() {
            return title;
        }

        /**
         * Sets the title that is displayed on this tab.
         *
         * @param title the title to be displayed on this tab
         * @throws NullPointerException     if the title is {@code null}
         * @throws IllegalArgumentException if the title is empty
         */
        public void setTitle(String title) {
            this.title = Objects.requireNonNull(title, "title");
            if (title.isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty");
            }
        }

        String getId() {
            return id;
        }

        void setId(String id) {
            this.id = id;
        }
    }

}
