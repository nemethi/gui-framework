package nemethi.gui;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class ButtonTest {

    private static final String BUTTON_OPENING = "<button type=\"button\" style=\"\">";
    private static final String BUTTON_CLOSING = "</button>";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void buttonTextIsEmptyByDefault() {
        assertThat(new Button().getText()).isEmpty();
    }

    @Test
    public void buttonTextCannotBeNull() {
        // when + then
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("text");
        new Button(null);

        // when + then
        Button button = new Button();
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("text");
        button.setText(null);
    }

    @Test
    public void getterWorks() {
        // given
        Button button = new Button("a");

        // when + then
        assertThat(button.getText()).isEqualTo("a");

        // when + then
        button.setText("b");
        assertThat(button.getText()).isEqualTo("b");
    }

    @Test
    public void renderButtonWithoutText() {
        // given
        String expected = BUTTON_OPENING + BUTTON_CLOSING;
        Button button = new Button();

        // when + then
        assertThat(button.render()).isEqualTo(expected);
    }

    @Test
    public void renderButtonWithText() {
        // given
        String expected = BUTTON_OPENING + "Lorem ipsum" + BUTTON_CLOSING;
        Button button = new Button("Lorem ipsum");

        // when + then
        assertThat(button.render()).isEqualTo(expected);
    }

    @Test
    public void equalsIsReflexive() {
        Button button = new Button();
        assertThat(button.equals(button)).isTrue();
    }

    @Test
    public void equalsReturnsFalseOnNull() {
        assertThat(new Button().equals(null)).isFalse();
    }

    @Test
    public void equalsReturnsFalseOnDifferentClass() {
        assertThat(new Button().equals(new Object())).isFalse();
    }

    @Test
    public void equalsReturnsFalseOnDifferentText() {
        // given
        Button button = new Button();
        Button differentButton = new Button("text");

        // when + then
        assertThat(button.equals(differentButton)).isFalse();
    }

    @Test
    public void equalButtonsHaveSameHashCode() {
        // given
        Button button = new Button();
        Button otherButton = new Button();

        // when + then
        assertThat(button.hashCode()).isEqualTo(otherButton.hashCode());
    }

    @Test
    public void toStringContainsFieldNamesAndValues() {
        Button button = new Button("a");
        assertThat(button.toString()).contains("text", button.getText(), "hidden", String.valueOf(button.isHidden()));
    }
}
