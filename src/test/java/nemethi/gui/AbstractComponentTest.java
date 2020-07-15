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
        assertThat(component.isHidden()).isFalse();
        assertThat(component.getDisplayCssProperty()).isEmpty();
    }

    @Test
    public void getDisplayCssPropertyReturnsValidCssRuleWhenHidden() {
        component.setHidden(true);
        assertThat(component.getDisplayCssProperty()).isEqualTo("display: none;");
    }
}
