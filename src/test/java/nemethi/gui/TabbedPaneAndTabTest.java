package nemethi.gui;

import nemethi.gui.TabbedPane.Tab;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(Enclosed.class)
public class TabbedPaneAndTabTest {

    private static final String TITLE = "title";
    private static final String TABCONTENT_STYLE = "style=\"display: none; padding: 6px 12px; border: 1px solid black; border-top: none;\"";

    public static class TabbedPaneTest {

        private static final String TABLINK_DIV_STYLE = "style=\"overflow: hidden; border: solid 1px black; background-color: #cccccc;\"";
        private static final String TABLINK_BUTTON_STYLE = "style=\"background-color: inherit; border: none; float: left; outline: none; padding: 14px 16px;\"";
        private static final String SHOW_TAB_JAVASCRIPT = "function showTab(e,t){var l,n,c;for(n=document.getElementsByClassName(\"tabcontent\"),l=0;l<n.length;l++)n[l].style.display=\"none\";for(c=document.getElementsByClassName(\"tablinks\"),l=0;l<c.length;l++)c[l].style.backgroundColor=\"#cccccc\";document.getElementById(t).style.display=\"block\",e.currentTarget.style.backgroundColor=\"#888888\"}document.getElementsByClassName(\"tablinks\")[0].click();";

        @Rule
        public ExpectedException thrown = ExpectedException.none();

        private TabbedPane tabbedPane;

        @Before
        public void setUp() {
            initMocks(this);
            tabbedPane = new TabbedPane();
        }

        @Test
        public void addTabThrowsExceptionOnNullTab() {
            thrown.expect(NullPointerException.class);
            thrown.expectMessage(is("tab"));
            tabbedPane.addTab(null);
        }

        @Test
        public void addTabMakesDefensiveCopy() {
            // given
            Tab tab = new Tab(TITLE);

            // when
            tabbedPane.addTab(tab);
            tab.setTitle("other title");

            // then
            List<Tab> tabs = tabbedPane.getTabs();
            assertThat(tabs).hasSize(1);
            Tab tabCopy = tabs.get(0);
            assertThat(tabCopy.getTitle()).isEqualTo("title");
        }

        @Test
        public void getTabsReturnsCopyOfTabs() {
            // given
            Tab tab = new Tab(TITLE);
            tabbedPane.addTab(tab);

            // when
            List<Tab> tabs = tabbedPane.getTabs();
            assertThat(tabs).hasSize(1);
            tabs.clear();

            // then
            assertThat(tabbedPane.getTabs()).hasSize(1);
        }

        @Test
        public void addTabAssignsIdToTabCopies() {
            // given
            Tab tab1 = new Tab("title1");
            Tab tab2 = new Tab("title2");
            tab1.setId("id1");
            tab2.setId("id2");


            // when
            tabbedPane.addTab(tab1);
            tabbedPane.addTab(tab2);

            // then
            Tab tabCopy1 = tabbedPane.getTabs().get(0);
            assertThat(tabCopy1.getId()).isEqualTo("Tabcontent1");
            Tab tabCopy2 = tabbedPane.getTabs().get(1);
            assertThat(tabCopy2.getId()).isEqualTo("Tabcontent2");
        }

        @Test
        public void renderTabbedPaneWithoutTabs() {
            // given
            String expected = "<div ><div " + TABLINK_DIV_STYLE + "></div><script>" +
                    SHOW_TAB_JAVASCRIPT + "</script></div>";

            // when + then
            assertThat(tabbedPane.render()).isEqualTo(expected);
        }

        @Test
        public void renderTabbedPane() {
            // given
            Tab tab = new Tab(TITLE);
            tab.setId("id");
            tabbedPane.addTab(tab);
            String expected = "<div >" +
                    "<div " + TABLINK_DIV_STYLE + ">" +
                    "<button class=\"tablinks\" onclick=\"showTab(event, 'Tabcontent1')\" " + TABLINK_BUTTON_STYLE + ">title</button>" +
                    "</div><div class=\"tabcontent\" id=\"Tabcontent1\" " + TABCONTENT_STYLE + "><table></table></div>" +
                    "<script>" + SHOW_TAB_JAVASCRIPT + "</script></div>";

            // when + then
            assertThat(tabbedPane.render()).isEqualTo(expected);
        }
    }

    public static class TabTest {

        @Rule
        public ExpectedException thrown = ExpectedException.none();

        @Mock
        private Component component;

        private Tab tab;

        @Before
        public void setUp() {
            initMocks(this);
            tab = new Tab(TITLE);
        }

        @Test
        public void tabTitleCannotBeNull() {
            thrown.expect(NullPointerException.class);
            thrown.expectMessage(is(TITLE));
            new Tab((String) null);
        }

        @Test
        public void tabTitleCannotBeEmpty() {
            thrown.expect(IllegalArgumentException.class);
            thrown.expectMessage(is("Title cannot be empty"));
            new Tab("");
        }

        @Test
        public void tabCopyConstructorThrowsExceptionOnNullTab() {
            thrown.expect(NullPointerException.class);
            thrown.expectMessage(is("tab"));
            new Tab((Tab) null);
        }

        @Test
        public void tabCopyConstructorWorks() {
            // given
            tab.setId("id");
            tab.addNextToLast(component);

            // when
            Tab copy = new Tab(tab);

            // then
            assertThat(copy.getTitle()).isEqualTo(tab.getTitle());
            assertThat(copy.getId()).isEqualTo(tab.getId());
            assertThat(copy.getComponents()).isEqualTo(tab.getComponents());
        }

        @Test
        public void setTitleThrowsExceptionOnNullTitle() {
            // when + then
            thrown.expect(NullPointerException.class);
            thrown.expectMessage(is(TITLE));
            tab.setTitle(null);
        }

        @Test
        public void setTitleThrowsExceptionOnEmptyTitle() {
            // when + then
            thrown.expect(IllegalArgumentException.class);
            thrown.expectMessage("Title cannot be empty");
            tab.setTitle("");
        }

        @Test
        public void titleGetterAndSetterWork() {
            // when + then
            assertThat(tab.getTitle()).isEqualTo(TITLE);

            // when
            tab.setTitle("other title");

            // then
            assertThat(tab.getTitle()).isEqualTo("other title");
        }

        @Test
        public void idIsEmptyByDefault() {
            assertThat(tab.getId()).isEmpty();
        }

        @Test
        public void idGetterAndSetterWork() {
            // when
            tab.setId("id");

            // then
            assertThat(tab.getId()).isEqualTo("id");
        }

        @Test
        public void renderOwnOpeningTag() {
            // given
            tab.setId("testId");
            String expected = "<div class=\"tabcontent\" id=\"testId\" " +
                    TABCONTENT_STYLE + ">";

            // when + then
            assertThat(tab.renderOwnOpeningTag()).isEqualTo(expected);
        }

        @Test
        public void renderOwnClosingTag() {
            assertThat(tab.renderOwnClosingTag()).isEqualTo("</div>");
        }
    }
}
