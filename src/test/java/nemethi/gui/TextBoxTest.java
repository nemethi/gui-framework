package nemethi.gui;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class TextBoxTest {

    private static final String ERROR_MESSAGE = "Size must be greater than zero";
    private static final String TEST = "test";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private TextBox textBox;

    @Before
    public void setUp() {
        textBox = new TextBox();
    }

    @Test
    public void textBoxFieldsHaveDefaultValues() {
        // when + then
        assertThat(textBox.getPlaceholder()).isEmpty();
        assertThat(textBox.getSize()).isEqualTo(20);
    }

    @Test
    public void placeholderCannotBeNull() {
        // when + then
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(is("placeholder"));
        new TextBox(null);
    }

    @Test
    public void setterThrowsExceptionOnNullPlaceholder() {
        // when + then
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(is("placeholder"));
        textBox.setPlaceholder(null);
    }

    @Test
    public void setterThrowsExceptionOnZeroSize() {
        // when + then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(is(ERROR_MESSAGE));
        textBox.setSize(0);
    }

    @Test
    public void setterThrowsExceptionOnNegativeSize() {
        // when + then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(is(ERROR_MESSAGE));
        textBox.setSize(-1);
    }

    @Test
    public void gettersAndSettersWork() {
        // given
        TextBox textBox = new TextBox("a");

        // when + then
        assertThat(textBox.getPlaceholder()).isEqualTo("a");

        // when
        textBox.setPlaceholder("b");
        textBox.setSize(1);

        // then
        assertThat(textBox.getPlaceholder()).isEqualTo("b");
        assertThat(textBox.getSize()).isEqualTo(1);
    }

    @Test
    public void renderTextBoxWithoutPlaceholder() {
        // given
        String expected = "<input type=\"text\" size=\"20\" />";

        // when + then
        assertThat(textBox.render()).isEqualTo(expected);
    }

    @Test
    public void renderTextBoxWithPlaceholder() {
        // given
        String expected = "<input type=\"text\" placeholder=\"" + TEST + "\" size=\"20\" />";
        textBox.setPlaceholder(TEST);

        // when + then
        assertThat(textBox.render()).isEqualTo(expected);
    }

    @Test
    public void renderHiddenTextBox() {
        // given
        String expected = "<input type=\"text\" placeholder=\"" + TEST + "\" size=\"20\" style=\"display: none;\"/>";
        textBox.setHidden(true);
        textBox.setPlaceholder(TEST);

        // when + then
        assertThat(textBox.render()).isEqualTo(expected);
    }
}
