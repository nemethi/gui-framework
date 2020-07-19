package nemethi.gui;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class LabelTest {

    private static final String TEST = "test";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Label label;

    @Before
    public void setUp() {
        label = new Label();
    }

    @Test
    public void labelTextIsEmptyByDefault() {
        assertThat(label.getText()).isEmpty();
    }

    @Test
    public void labelTextCannotBeNull() {
        // when + then
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(is("text"));
        new Label(null);
    }

    @Test
    public void setterThrowsExceptionOnNullText() {
        // when + then
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(is("text"));
        label.setText(null);
    }

    @Test
    public void getterAndSetterWork() {
        // given
        Label label = new Label("a");

        // when + then
        assertThat(label.getText()).isEqualTo("a");

        // when
        label.setText("b");

        // then
        assertThat(label.getText()).isEqualTo("b");
    }

    @Test
    public void renderLabelWithText() {
        // given
        String expected = "<label >" + TEST + "</label>";
        label.setText(TEST);

        // when + then
        assertThat(label.render()).isEqualTo(expected);
    }

    @Test
    public void renderHiddenLabel() {
        // given
        String expected = "<label style=\"display: none;\">" + TEST + "</label>";
        label.setHidden(true);
        label.setText(TEST);

        // when + then
        assertThat(label.render()).isEqualTo(expected);
    }
}
