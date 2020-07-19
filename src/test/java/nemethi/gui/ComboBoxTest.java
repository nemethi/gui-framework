package nemethi.gui;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.list;

public class ComboBoxTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private List<String> testItems;
    private ComboBox comboBox;

    @Before
    public void setUp() {
        comboBox = new ComboBox();
        testItems = list("a", "b");
    }

    @Test
    public void itemListIsEmtpyByDefault() {
        assertThat(comboBox.getItems()).isEmpty();
    }

    @Test
    public void itemListCannotBeNull() {
        // when + then
        thrown.expect(NullPointerException.class);
        new ComboBox(null);
    }

    @Test
    public void setterThrowsExceptionOnNullItemList() {
        // when + then
        thrown.expect(NullPointerException.class);
        comboBox.setItems(null);
    }

    @Test
    public void constructorMakesDefensiveCopy() {
        // given

        // when
        ComboBox comboBox = new ComboBox(testItems);
        testItems.add("c");

        // then
        assertThat(comboBox.getItems()).isEqualTo(list("a", "b"));
    }

    @Test
    public void setterMakesDefensiveCopy() {
        // given

        // when
        comboBox.setItems(testItems);
        testItems.add("c");

        // then
        assertThat(comboBox.getItems()).isEqualTo(list("a", "b"));
    }

    @Test
    public void getterReturnsCopyOfItems() {
        // given
        comboBox.setItems(testItems);

        // when
        List<String> items = comboBox.getItems();
        items.add("c");

        // then
        assertThat(comboBox.getItems()).isEqualTo(testItems);
    }

    @Test
    public void renderEmptyComboBox() {
        // given
        String expected = "<select ></select>";

        // when + then
        assertThat(comboBox.render()).isEqualTo(expected);
    }

    @Test
    public void renderComboBoxWithItems() {
        // given
        String expected = "<select ><option>a</option><option>b</option></select>";
        comboBox.setItems(testItems);

        // when + then
        assertThat(comboBox.render()).isEqualTo(expected);
    }

    @Test
    public void renderHiddenComboBox() {
        // given
        String expected = "<select style=\"display: none;\">" +
                "<option>a</option><option>b</option></select>";
        comboBox.setItems(testItems);
        comboBox.setHidden(true);

        // when + then
        assertThat(comboBox.render()).isEqualTo(expected);
    }
}
