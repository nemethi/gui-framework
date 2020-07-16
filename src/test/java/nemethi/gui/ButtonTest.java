package nemethi.gui;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class ButtonTest {

    private static final String BUTTON_OPENING = "<button type=\"button\" >";
    private static final String BUTTON_STYLE_OPENING = "<button type=\"button\" style=\"display: none;\">";
    private static final String BUTTON_CLOSING = "</button>";
    private static final String TEST = "test";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Button button;

    @Before
    public void setUp() {
        button = new Button();
    }

    @Test
    public void buttonTextIsEmptyByDefault() {
        assertThat(button.getText()).isEmpty();
    }

    @Test
    public void buttonTextCannotBeNull() {
        // when + then
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(is("text"));
        new Button(null);
    }

    @Test
    public void setterThrowsExceptionOnNullText() {
        // when + then
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(is("text"));
        button.setText(null);
    }

    @Test
    public void getterAndSetterWork() {
        // given
        Button button = new Button("a");

        // when + then
        assertThat(button.getText()).isEqualTo("a");

        // when
        button.setText("b");

        // then
        assertThat(button.getText()).isEqualTo("b");
    }

    @Test
    public void renderButtonWithoutText() {
        // given
        String expected = BUTTON_OPENING + BUTTON_CLOSING;

        // when + then
        assertThat(button.render()).isEqualTo(expected);
    }

    @Test
    public void renderButtonWithText() {
        // given
        String expected = BUTTON_OPENING + TEST + BUTTON_CLOSING;
        button.setText(TEST);

        // when + then
        assertThat(button.render()).isEqualTo(expected);
    }

    @Test
    public void renderHiddenButton() {
        // given
        String expected = BUTTON_STYLE_OPENING + TEST + BUTTON_CLOSING;
        button.setHidden(true);
        button.setText(TEST);

        // when + then
        assertThat(button.render()).isEqualTo(expected);
    }
}
