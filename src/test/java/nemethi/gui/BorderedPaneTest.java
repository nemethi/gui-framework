package nemethi.gui;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BorderedPaneTest {

    private BorderedPane pane;

    @Before
    public void setUp() {
        pane = new BorderedPane();
    }

    @Test
    public void renderOwnOpeningTag() {
        // given
        String expected = "<div style=\"border: 1px solid black;\">";

        // when + then
        assertThat(pane.renderOwnOpeningTag()).isEqualTo(expected);
    }

    @Test
    public void renderOwnOpeningTagWhenHidden() {
        // given
        String expected = "<div style=\"border: 1px solid black;display: none;\">";
        pane.setHidden(true);

        // when + then
        assertThat(pane.renderOwnOpeningTag()).isEqualTo(expected);
    }

    @Test
    public void renderOwnClosingTag() {
        assertThat(pane.renderOwnClosingTag()).isEqualTo("</div>");
    }
}
