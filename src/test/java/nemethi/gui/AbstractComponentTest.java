package nemethi.gui;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractComponentTest {

    private AbstractComponent component;

    @Before
    public void setUp() {
        component = new AbstractComponent() {
            @Override
            public String render() {
                return null;
            }
        };
    }

    @Test
    public void getDisplayCssPropertyReturnsEmptyStringWhenNotHidden() {
        // given
        assertThat(component.isHidden()).isFalse();

        // when + then
        assertThat(component.getDisplayCssProperty()).isEmpty();
    }

    @Test
    public void getDisplayCssPropertyReturnsValidCssRuleWhenHidden() {
        // given
        component.setHidden(true);

        // when + then
        assertThat(component.getDisplayCssProperty()).isEqualTo("display: none;");
    }

    @Test
    public void getStyleWithDisplayReturnsEmptyStringWhenNotHidden() {
        // given
        assertThat(component.isHidden()).isFalse();

        // when + then
        assertThat(component.getStyleWithDisplay()).isEmpty();
    }

    @Test
    public void getStyleWithDisplayReturnsValidHtmlAttributeWhenHidden() {
        // given
        String expected = "style=\"display: none;\"";
        component.setHidden(true);

        // when + then
        assertThat(component.getStyleWithDisplay()).isEqualTo(expected);
    }
}
